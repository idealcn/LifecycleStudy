package com.idealcn.lifecycle.study.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.base.BaseActivity
import com.idealcn.lifecycle.study.bean.Chapter
import com.idealcn.lifecycle.study.bean.ChapterHistory
import com.idealcn.lifecycle.study.dagger.component.DaggerChapterComponent
import com.idealcn.lifecycle.study.exception.BaseThrowable
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.Api
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.transformer.RxErrorHandler
import com.idealcn.lifecycle.study.ui.adapter.AbstractBaseAdapter
import com.idealcn.lifecycle.study.ui.adapter.AbstractBaseHolder
import com.idealcn.lifecycle.study.ui.mvp.presenter.ChapterPresenter
import com.idealcn.lifecycle.study.ui.mvp.view.ChapterView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_tencent_chapter.*
import kotlinx.android.synthetic.main.adapter_chapter.view.*
import javax.inject.Inject

/**
 * @author: guoning
 * @date: 2018/12/6 13:28
 * @description: 公众号页面
 */
class TencentChapterActivity : BaseActivity() ,ChapterView{
    override fun showRequestResult(history: ChapterHistory) {

        pageMap[tabList[lastSelectTabIndex]] = getPageIndexOfTab(lastSelectTabIndex)

         val arrayList : ArrayList<ChapterHistory.ChapterHistoryChild> = history.datas
                        map.put(tabList[lastSelectTabIndex],arrayList)
                        adapter.clearData()
                        adapter.addData(arrayList)
    }

    override fun showRequestProgress(msg: String) {
        toast(msg)
    }

    override fun showRequestSuccess(msg: String) {
        toast(msg)
    }

    /**
     * 缓存当前页面和前后两个页面的数据
     */
    val map : HashMap<String,ArrayList<ChapterHistory.ChapterHistoryChild>> = hashMapOf()

    val tabMap : HashMap<String, Chapter> = hashMapOf()

    val tabList : ArrayList<String> = arrayListOf()

    val pageMap : HashMap<String,Int> = hashMapOf()

    var lastSelectTabIndex = 0

    @Inject
    lateinit var presenter : ChapterPresenter

    private val  adapter = object : AbstractBaseAdapter<ChapterHistory.ChapterHistoryChild>(){
        override fun getLayout(): Int {
            return R.layout.adapter_chapter
        }

        override fun onBindNormalHolder(
            holder: AbstractBaseHolder,
            position: Int,
            child: ChapterHistory.ChapterHistoryChild
        ) {
            holder.itemView.title.text = child.chapterName
            holder.itemView.author.text = child.author

        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_tencent_chapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerChapterComponent.builder().build().inject(this)
        presenter.attach(this)

        RetrofitClient.newInstance().api.chapterList().ext()
            .doOnSubscribe {

            }.compose(RxErrorHandler.handlerError(this))
            .flatMap { t ->
                val errorCode = t.errorCode
                when (errorCode){
                    Api.ErrorCode.CODE_0 -> {
                        Observable.just(t.data)
                    }
                    else -> {
                        Observable.error(BaseThrowable(t.errorMsg,errorCode))
                    }
                }
            }
            .flatMap { list ->
                list.forEach { chapter ->
                    tabList.add(chapter.id.toString())
                    tabMap.put(chapter.id.toString(),chapter)
                    tabLayout.addTab( tabLayout.newTab().setText(chapter.name))
                }
                RetrofitClient.newInstance().api.chapterHistoryList(list[0].id,0).ext()
                    .compose(RxErrorHandler.handlerError(this@TencentChapterActivity))
            }
            .subscribe({
               val errorCode = it.errorCode
                when (errorCode) {
                    Api.ErrorCode.CODE_0 -> {
                        val data = it.data
                        val arrayList : ArrayList<ChapterHistory.ChapterHistoryChild> = data.datas
                        adapter.addData(arrayList)
                        map.put(tabList[lastSelectTabIndex],arrayList)
                        chapterHistoryList.adapter = adapter
                        chapterHistoryList.addItemDecoration(DividerItemDecoration(this@TencentChapterActivity,DividerItemDecoration.VERTICAL))
                        chapterHistoryList.layoutManager = LinearLayoutManager(this@TencentChapterActivity)
                    }
                    else -> {

                    }
                }

            },{
                toast(it.message!!)
            },{

            })



        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {


                val position = tab!!.position

                if (lastSelectTabIndex == position)return

                lastSelectTabIndex = position


                val arrayList = map[tabList[position]]
                arrayList?.run {
                    adapter.clearData()
                    adapter.addData(this)
                    return
                }

                val page = getPageIndexOfTab(position)


                presenter.loadChapterList(tabMap[tabList[position]]!!.id,page)
            }
        })


//        chapterRefreshLayout.autoRefresh()
        chapterRefreshLayout.setOnRefreshListener {
            pageMap[tabList[lastSelectTabIndex]] = 0
            presenter.loadChapterList(tabMap[tabList[lastSelectTabIndex]]!!.id,0)
        }

        chapterRefreshLayout.setOnLoadMoreListener {
            val page = getPageIndexOfTab(lastSelectTabIndex)
            presenter.loadChapterList(tabMap[tabList[lastSelectTabIndex]]!!.id,page)
        }


    }

    private fun getPageIndexOfTab(position: Int): Int {
        var page = pageMap[tabList[position]]
        page = page?.plus(1) ?: 0
        return page
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}