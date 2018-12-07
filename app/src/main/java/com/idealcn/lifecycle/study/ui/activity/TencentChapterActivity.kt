package com.idealcn.lifecycle.study.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.base.BaseActivity
import com.idealcn.lifecycle.study.bean.Chapter
import com.idealcn.lifecycle.study.bean.ChapterHistory
import com.idealcn.lifecycle.study.dagger.component.DaggerChapterComponent
import com.idealcn.lifecycle.study.databinding.AdapterChapterBinding
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
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_tencent_chapter.*
import kotlinx.android.synthetic.main.adapter_chapter.view.*
import java.lang.RuntimeException
import javax.inject.Inject

/**
 * @author: guoning
 * @date: 2018/12/6 13:28
 * @description: 公众号页面
 */
class TencentChapterActivity : BaseActivity(), ChapterView {
    override fun showRequestResult(history: ChapterHistory) {

        if (isRefreshing) chapterRefreshLayout.finishRefresh() else chapterRefreshLayout.finishLoadMore()

        pageMap[tabList[lastSelectTabIndex]] = getPageIndexOfTab(lastSelectTabIndex)

        val arrayList: ArrayList<ChapterHistory.ChapterHistoryChild> = history.datas
        map[tabList[lastSelectTabIndex]] = arrayList

        //在切换tab的时候才去清除数据
        if (switchTab) {
            adapter.clearData()
            switchTab = false
        }
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
    val map: HashMap<String, ArrayList<ChapterHistory.ChapterHistoryChild>> = hashMapOf()

    val tabMap: HashMap<String, Chapter> = hashMapOf()

    val tabList: ArrayList<String> = arrayListOf()

    val pageMap: HashMap<String, Int> = hashMapOf()

    var lastSelectTabIndex = 0

    private var isRefreshing = false

    private var switchTab = false

    private val compositeDisposable :CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var presenter: ChapterPresenter

    private val adapter = object : AbstractBaseAdapter<ChapterHistory.ChapterHistoryChild, AdapterChapterBinding>() {
        override fun getLayout(): Int {
            return R.layout.adapter_chapter
        }

        override fun onBindNormalHolder(
            holder: AbstractBaseHolder<AdapterChapterBinding>,
            position: Int,
            child: ChapterHistory.ChapterHistoryChild
        ) {
            holder.dataBinding.child = child
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_tencent_chapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerChapterComponent.builder().build().inject(this)
        presenter.attach(this)

        chapterHistoryList.adapter = adapter
//        chapterHistoryList.layoutManager = LinearLayoutManager(this)
        chapterHistoryList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        compositeDisposable.add(
            RetrofitClient.newInstance().api.chapterList().ext()
                .doOnSubscribe {

                }.compose(RxErrorHandler.handlerError(this))
                .flatMap { t ->
                    val errorCode = t.errorCode
                    when (errorCode) {
                        Api.ErrorCode.CODE_0 -> {
                            Observable.just(t.data)

                        }
                        else -> {
                            Observable.error(BaseThrowable(t.errorMsg, errorCode))
                        }
                    }
                }
                .flatMap { list ->
                    list.forEach { chapter ->
                        tabList.add(chapter.id.toString())
                        tabMap.put(chapter.id.toString(), chapter)
                        tabLayout.addTab(tabLayout.newTab().setText(chapter.name))
                    }
                    Observable.just(true)
                }
                .subscribe({
                    chapterRefreshLayout.autoRefresh(1)
                }, {

                    it.message?.let { it1 -> toast(it1) }
                }, {

                })
        )



        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

                tab?.run {
                    val position = tab.position

                    if (lastSelectTabIndex == position) return

                    lastSelectTabIndex = position

                    switchTab = true

                    val arrayList = map[tabList[position]]
                    arrayList?.run {
                        adapter.clearData()
                        adapter.addData(this)
                        return
                    }
                    chapterRefreshLayout.autoRefresh()
                }


            }
        })


//        chapterRefreshLayout.autoRefresh()
        chapterRefreshLayout.setOnRefreshListener {
            isRefreshing = true
            pageMap[tabList[lastSelectTabIndex]] = 0
            presenter.loadChapterList(tabMap[tabList[lastSelectTabIndex]]!!.id, 0)
        }

        chapterRefreshLayout.setOnLoadMoreListener {
            isRefreshing = false
            val page = getPageIndexOfTab(lastSelectTabIndex)
            presenter.loadChapterList(tabMap[tabList[lastSelectTabIndex]]!!.id, page)
        }


        setSupportActionBar(toolBar)
//        TODO("研究一下toolbar的用法")
        //toolBar.title = "公众号"//这个无效果
//        supportActionBar?.setTitle("公众号") ?: throw RuntimeException("不支持ActionBar")
        collapsingToolbarLayout.title = "公众号"
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#ffffff"))
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this,R.color.colorPrimary))
        toolBar.setTitleTextColor(Color.parseColor("#ffffff"))

    }

    /**
     * 获取当前选项卡的加载页数
     */
    private fun getPageIndexOfTab(tabIndex: Int): Int {
        var page = pageMap[tabList[tabIndex]]
        page = page?.plus(1) ?: 0
        return page
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}