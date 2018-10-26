package com.idealcn.lifecycle.study.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idealcn.lifecycle.study.CollectDecoration
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.adapter.HomeArticleAdapter
import com.idealcn.lifecycle.study.bean.Article
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import com.idealcn.lifecycle.study.ui.ArticleDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment.*
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private  var page : Int = 0
    private   lateinit      var disposable: Disposable
    private lateinit var _context : Context


    private lateinit var decoration : CollectDecoration

    private lateinit var articleAdapter: HomeArticleAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this._context = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        decoration =   CollectDecoration(_context)


        homeList.layoutManager = LinearLayoutManager(_context)
        homeList.addItemDecoration(decoration)
        articleAdapter = HomeArticleAdapter(_context)
        homeList.adapter = articleAdapter
        articleAdapter.setOnAdapterItemClickListener(object : HomeArticleAdapter.OnAdapterItemClickListener{
            override fun onItemClick(position: Int) {
                val article = articleAdapter.getData()[position]
                startActivity(Intent(_context,ArticleDetailActivity::class.java).putExtra("article",article))
            }

        })
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(_context,R.color.colorPrimary))
        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }


        val mHeaderLayout = layoutInflater.inflate(R.layout.layout_home_header,homeList,false)
//        val layoutManager = homeList.layoutManager as LinearLayoutManager
//        layoutManager.addView(mHeaderLayout,0)



    }

    private fun refreshData() {
        page = 0
        loadData(true)
    }

    private fun loadData(refresh :Boolean) {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        disposable = viewModel.homeArticleList(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                swipeRefreshLayout.isRefreshing = false
                val list = it.datas
                //刷新的数据与最前面的数据做比较
                if (refresh){
                    decoration.setData(0,list)

                    val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                           return articleAdapter.getData()[oldItemPosition].chapterId == list[newItemPosition].chapterId
                        }

                        override fun getOldListSize(): Int {
                            return  newListSize
                        }

                        override fun getNewListSize(): Int {
                           return list.size
                        }

                        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                            return articleAdapter.getData()[oldItemPosition].chapterName == list[newItemPosition].chapterName
                        }

                    })
                    diffResult.dispatchUpdatesTo(articleAdapter)


                }else {
                    decoration.setData(decoration.getData().size,list)
                    articleAdapter.setData(articleAdapter.getData().size,list)
                }
            }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadData(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
           disposable.dispose()
       }
}


