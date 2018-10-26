package com.idealcn.lifecycle.study.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import com.idealcn.lifecycle.study.Api
import com.idealcn.lifecycle.study.bean.Article
import com.idealcn.lifecycle.study.bean.BaseResponseBean
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import com.idealcn.lifecycle.study.converter.LiveDataConverterFactory
import com.idealcn.lifecycle.study.exception.BaseThrowable
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun homeArticleList(page : Int) : Observable<HomeArticleBean> {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.wanandroid.com/")
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val articleList = retrofit.create(Api::class.java)
            .articleList(page)

      return  articleList.flatMap {
            val errorCode = it.errorCode
            if (errorCode == 0) {
                val data = it.data
                Observable.just(data)
            } else {
                Observable.error(BaseThrowable(it.errorMsg))
            }
        }
    }


}
