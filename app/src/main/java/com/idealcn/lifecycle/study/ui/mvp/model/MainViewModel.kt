package com.idealcn.lifecycle.study.ui.mvp.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.idealcn.lifecycle.study.AppApplication
import com.idealcn.lifecycle.study.AppHelper
import com.idealcn.lifecycle.study.bean.Article
import com.idealcn.lifecycle.study.bean.BaseResponseBean
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import com.idealcn.lifecycle.study.exception.BaseThrowable
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.Api
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.transformer.RxDialog
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class MainViewModel : ViewModel() {

    public val dataRepository = MutableLiveData<List<Article>>()


    // TODO: Implement the ViewModel
    fun homeArticleList(page : Int) : Observable<HomeArticleBean> {

      return  RetrofitClient.newInstance().api.articleList(page)
          .ext()
          //.compose(RxErrorHandler.handlerError(AppApplication.getAppContext()))
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

        //articleList

        //AppHelper.getHelper().getApi().articleList(page)

        return  RetrofitClient.newInstance().api.getArticleList(page)

    }

    fun loadArticleList(page : Int)  {
      RetrofitClient.newInstance().api.articleList(page)
            .ext()
            //.compose(RxErrorHandler.handlerError(AppApplication.getAppContext()))
          .flatMap {
            if(it.errorCode==Api.ErrorCode.CODE_0 )
             Observable.just( it.data)
              else
                Observable.error(Throwable(it.errorMsg))
          }
          .subscribe(object : io.reactivex.Observer<HomeArticleBean>{

              override fun onComplete() {

              }

              override fun onSubscribe(d: Disposable) {
              }

              override fun onNext(t: HomeArticleBean) {
                  dataRepository.postValue(t.datas)
              }

              override fun onError(e: Throwable) {
              }

          })
    }


}
