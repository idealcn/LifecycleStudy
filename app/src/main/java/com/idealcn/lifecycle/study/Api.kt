package com.idealcn.lifecycle.study

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.idealcn.lifecycle.study.bean.Article
import com.idealcn.lifecycle.study.bean.BaseResponseBean
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {


    //http://www.wanandroid.com/article/list/0/json
    @GET("article/list/{page}/json")
    fun articleList(@Path("page") page : Int) : Observable<BaseResponseBean<HomeArticleBean>>


    /**
     * http://www.wanandroid.com/lg/collect/1165/json
     * 收藏文章
     */
    @POST("lg/collect/{id}/json")
    fun collect(@Path("id")id : Int) : Observable<BaseResponseBean<Boolean>>


}