package com.idealcn.lifecycle.study.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idealcn.lifecycle.study.CollectDecoration
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import com.idealcn.lifecycle.study.ui.ArticleDetailActivity
import com.idealcn.lifecycle.study.ui.adapter.HomeArticleAdapter
import com.idealcn.lifecycle.study.ui.dagger2.component.DaggerMainComponent
import com.idealcn.lifecycle.study.ui.mvp.model.MainViewModel
import com.idealcn.lifecycle.study.ui.mvp.view.MainView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_home.*

class MainFragment : BaseFragment(),MainView {

    private lateinit var articleAdapter  : HomeArticleAdapter

    private var page = 0


    private lateinit var decoration :CollectDecoration


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //注入
        DaggerMainComponent.builder().build().injectFragment(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initViews() {
        decoration = CollectDecoration(_context)
        homeRecyclerView.layoutManager = LinearLayoutManager(_context)
        homeRecyclerView.addItemDecoration(decoration)
        articleAdapter = HomeArticleAdapter(_context)
        homeRecyclerView.adapter = articleAdapter
        articleAdapter.setOnAdapterItemClickListener(object : HomeArticleAdapter.OnAdapterItemClickListener{
            override fun onItemClick(position: Int) {
                val article = articleAdapter.getData()[position]
                startActivity(Intent(_context, ArticleDetailActivity::class.java).putExtra("article",article))
            }

        })
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(_context,R.color.colorPrimary))
        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        articleAdapter.getData().forEach forEach@{
            if (it.chapterName == "hello")return@forEach
            println(it.author)
        }

        val a = {x : Int, y : Int -> x+y }

    }


    private fun refreshData() {
        val subscriber = ViewModelProviders.of(this).get(MainViewModel::class.java)
            .homeArticleList(page)
            .subscribeWith(observer)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun loadData() {

        refreshData()

    }


    private val observer : Observer<HomeArticleBean> = object  : Observer<HomeArticleBean>{
        override fun onComplete() {
            swipeRefreshLayout.isRefreshing = false
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: HomeArticleBean) {
            val list = t.datas
            decoration.setData(decoration.getData().size,list)
            articleAdapter.setData(articleAdapter.getData().size,list)
        }

        override fun onError(e: Throwable) {
        }

    }

}