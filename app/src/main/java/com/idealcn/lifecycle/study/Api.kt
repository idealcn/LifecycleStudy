package com.idealcn.lifecycle.study

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.idealcn.lifecycle.study.bean.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

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


    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("wxarticle/chapters/json")
    fun chapterList() : Single<BaseResponseBean<List<Chapter>>>


    /**
     * 获取某个公众号历史数据
     * http://wanandroid.com/wxarticle/list/405/1/json
     */
    @GET("wxarticle/list/{chapterId}/{pageIndex}/json")
    fun chapterHistoryList(@Path("chapterId")chapterId : Int,@Path("pageIndex")pageIndex : Int) : Single<BaseResponseBean<ChapterHistory>>


    /**
     * 注册
     * http://www.wanandroid.com/user/register
     */
    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("username")username :String,@Field("password")password : String,
                 @Field("repassword")repassword :String) : Single<BaseResponseBean<AppUser>>
}