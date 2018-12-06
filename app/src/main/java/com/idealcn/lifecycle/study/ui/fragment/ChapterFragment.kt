package com.idealcn.lifecycle.study.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.Chapter
import com.idealcn.lifecycle.study.bean.ChapterHistory
import com.idealcn.lifecycle.study.databinding.AdapterChapterBinding
import com.idealcn.lifecycle.study.ui.adapter.AbstractBaseAdapter
import com.idealcn.lifecycle.study.ui.adapter.AbstractBaseHolder
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.adapter_chapter.view.*
import kotlinx.android.synthetic.main.fragment_chapter.*
import java.util.*

class ChapterFragment : Fragment() {

    private var isViewCreated = false
    private lateinit var chapter: Chapter

    private var pageIndex = 0

    private lateinit var _context : Context


    private val dataList = ArrayList<ChapterHistory.ChapterHistoryChild>()

    private lateinit var   observer: Observer<ArrayList<ChapterHistory.ChapterHistoryChild>>


    private val adapter : AbstractBaseAdapter<ChapterHistory.ChapterHistoryChild, AdapterChapterBinding> = object : AbstractBaseAdapter<ChapterHistory.ChapterHistoryChild,AdapterChapterBinding>(dataList){
        override fun onBindNormalHolder(
            holder: AbstractBaseHolder<AdapterChapterBinding>,
            position: Int,
            t: ChapterHistory.ChapterHistoryChild
        ) {
//            val chapter = list[position]
//            holder.itemView.title.text = chapter.title
//            holder.itemView.author.text = String.format(Locale.CHINESE,"by %s",chapter.author)
//            holder.itemView.superChapterName.text = chapter.superChapterName
        }

        override fun getLayout(): Int {
            return R.layout.adapter_chapter
        }

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser&&isViewCreated){
            chapter = arguments?.getSerializable("chapter") as Chapter
            loadData()
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this._context = context
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chapter,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true

        chapterHistoryList.layoutManager = LinearLayoutManager(_context)
        chapterHistoryList.addItemDecoration(DividerItemDecoration(_context,DividerItemDecoration.VERTICAL))
        chapterHistoryList.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       userVisibleHint  = true

        observer = object  : Observer<ArrayList<ChapterHistory.ChapterHistoryChild>>{
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: ArrayList<ChapterHistory.ChapterHistoryChild>) {
               adapter.addData(t)
            }

            override fun onError(e: Throwable) {

            }


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        isViewCreated = false
        userVisibleHint = false
    }



    private fun loadData() {
//        val disposable = RetrofitClient.newInstance().api.chapterHistoryList(chapterId = chapter.id, pageIndex = pageIndex )
////            .compose(ErrorTransformer<>)
//            .ext().subscribe({
//                val errorCode = it.errorCode
//                val data :ArrayList<ChapterHistory.ChapterHistoryChild> = it.data.datas
//                if (errorCode==0){
//                    Observable.fromArray(data).subscribeWith(observer)
//                }
//
//            }, {
//                it.message
//            })
    }


}