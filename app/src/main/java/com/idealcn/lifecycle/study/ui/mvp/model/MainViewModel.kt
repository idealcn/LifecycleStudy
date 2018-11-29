package com.idealcn.lifecycle.study.ui.mvp.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.idealcn.lifecycle.study.AppApplication
import com.idealcn.lifecycle.study.bean.BaseResponseBean
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import com.idealcn.lifecycle.study.exception.BaseThrowable
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.transformer.RxDialog
import com.idealcn.lifecycle.study.transformer.RxErrorHandler
import io.reactivex.Flowable
import io.reactivex.Observable
import org.reactivestreams.Publisher

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun homeArticleList(page : Int) : Observable<HomeArticleBean> {

      return  RetrofitClient.newInstance().api.articleList(page)
          .ext()
          .compose(RxErrorHandler.handlerError(AppApplication.getAppContext()))
          .doOnSubscribe {
              println("----------------doOnSubscribe-------------------")
              RxDialog.showProgressDialog(AppApplication.getAppContext(),"加载中")
                  .doOnNext {

                  }
          }
          .doOnComplete {

          }
          .flatMap {
              when (it.errorCode){
                  0 -> Observable.just(it.data)
                  else ->  Observable.error(BaseThrowable(it.errorMsg,it.errorCode))
              }
        }
          .doFinally {

          }
    }

    fun getArticleList(page : Int) : LiveData<BaseResponseBean<HomeArticleBean>> {

        return  RetrofitClient.newInstance().api.getArticleList(page)

    }

    fun loadArticleList(page : Int) : Flowable<HomeArticleBean> {
     return RetrofitClient.newInstance().api.loadArticleList(page)
            .ext()
            .compose(RxErrorHandler.handlerError(AppApplication.getAppContext()))

          .flatMap { base ->
                if (base.errorCode == 200){
                    Flowable.just(base.data)
                }else {
                    Flowable.error(BaseThrowable(base.errorMsg,base.errorCode))
                }
            }
    }


}
