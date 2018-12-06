package com.idealcn.lifecycle.study.ui.mvp.presenter

import com.idealcn.lifecycle.study.bean.ChapterHistory
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.Api
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.transformer.RxErrorHandler
import com.idealcn.lifecycle.study.ui.activity.TencentChapterActivity
import com.idealcn.lifecycle.study.ui.mvp.contract.ChapterContract
import com.idealcn.lifecycle.study.ui.mvp.view.ChapterView
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * @author: guoning
 * @date: 2018/12/6 16:05
 * @description:
 */
class ChapterPresenter @Inject constructor() : ChapterContract.Presenter<ChapterView>{

    override fun attach(view: ChapterView) {
        this.chapterView = WeakReference(view)
    }

    override fun detach() {
        chapterView.clear()
    }
    private lateinit var chapterView :WeakReference<ChapterView>


    fun loadChapterList(tabId : Int,page : Int){
        RetrofitClient.newInstance().api.chapterHistoryList(tabId,page)
            .ext()
            .compose(RxErrorHandler.handlerError(chapterView.get()!! as TencentChapterActivity))
            .doOnSubscribe {
                chapterView.get()?.showRequestProgress("请求中")
            }
            .doOnNext {
                chapterView.get()?.showRequestSuccess("请求成功")
            }
            .subscribe({
                val errorCode = it.errorCode
                when (errorCode) {
                    Api.ErrorCode.CODE_0 -> {
                        val data = it.data
                        chapterView.get()?.showRequestResult(data)
                    }
                    else -> {

                    }
                }
            },{

            },{

            })
    }
}