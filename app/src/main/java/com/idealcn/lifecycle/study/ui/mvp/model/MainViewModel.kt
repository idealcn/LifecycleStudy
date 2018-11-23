package com.idealcn.lifecycle.study.ui.mvp.model

import android.arch.lifecycle.ViewModel
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import com.idealcn.lifecycle.study.exception.BaseThrowable
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.RetrofitClient
import io.reactivex.Observable

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun homeArticleList(page : Int) : Observable<HomeArticleBean> {

      return  RetrofitClient.newInstance().api.articleList(page)
          .ext()
          .flatMap {
              when (it.errorCode){
                  0 -> Observable.just(it.data)
                  else ->  Observable.error(BaseThrowable(it.errorMsg))
              }
        }
    }


}
