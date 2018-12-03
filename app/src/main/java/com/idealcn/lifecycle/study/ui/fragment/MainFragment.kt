package com.idealcn.lifecycle.study.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import com.idealcn.lifecycle.study.dagger.component.DaggerHomeComponent
import com.idealcn.lifecycle.study.decoration.CollectDecoration
import com.idealcn.lifecycle.study.ui.activity.ArticleDetailActivity
import com.idealcn.lifecycle.study.ui.adapter.HomeArticleAdapter
import com.idealcn.lifecycle.study.ui.mvp.model.HomeModel
import com.idealcn.lifecycle.study.ui.mvp.presenter.HomePresenter
import com.idealcn.lifecycle.study.ui.mvp.view.HomeView
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class MainFragment : BaseFragment<HomeView>(),HomeView {


    private lateinit var articleAdapter  : HomeArticleAdapter

    private var page = 0


    private lateinit var decoration : CollectDecoration


    @Inject
    lateinit var presenter :HomePresenter



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


//        ViewModelProviders.of(this).get(HomeModel::class.java)
//            .liveData.observe(this,
//            Observer<HomeArticleBean> { data ->
//                data?.let {
//                    val list = it.datas
//                    decoration.setData(decoration.getData().size,list)
//                    articleAdapter.setData(articleAdapter.getData().size,list)
//                }
//            })



    }


    private fun refreshData() {
        presenter.loadArticleList(page)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun loadData() {
        DaggerHomeComponent.builder().build().inject(this)
        presenter.attach(this)
        refreshData()
    }

    override fun init() {

    }


    override fun showRequestProgress() {
        toast("请求中")
//        showRequestDialog()
    }

    override fun hideRequestProgress() {
        toast("请求完毕")
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showRequestResult(data: HomeArticleBean) {
        val list = data.datas
        decoration.setData(decoration.getData().size,list)
        articleAdapter.setData(articleAdapter.getData().size,list)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }



}