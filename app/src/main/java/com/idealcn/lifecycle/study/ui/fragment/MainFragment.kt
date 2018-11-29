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
import com.idealcn.lifecycle.study.ui.mvp.model.MainViewModel
import com.idealcn.lifecycle.study.ui.mvp.view.MainView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_home.*

class MainFragment : BaseFragment(),MainView {

    private lateinit var articleAdapter  : HomeArticleAdapter

    private var page = 0


    private lateinit var decoration :CollectDecoration




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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


    }


    private fun refreshData() {
        //在同一个Fragment或者Activity中,多次获取的ViewModel都是同一个对象
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//        val subscriber = viewModel
//            .homeArticleList(page)
//            .subscribeWith(observer)

//        viewModel.getArticleList(page)
//            .observe(this,android.arch.lifecycle.Observer {
//                it?.let { baseResponseBean ->
//
//                    val data = baseResponseBean.data
//                    val list = data.datas
//                    decoration.setData(decoration.getData().size,list)
//                    articleAdapter.setData(articleAdapter.getData().size,list)
//                }
//
//            })

        compositeDisposable.add(viewModel.homeArticleList(page)
            .subscribe { bean ->
                val list = bean.datas
                decoration.setData(decoration.getData().size,list)
                articleAdapter.setData(articleAdapter.getData().size,list)
            })
    }

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun loadData() {

        refreshData()

    }


    private val observer : io.reactivex.Observer<HomeArticleBean> = object  : io.reactivex.Observer<HomeArticleBean> {
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