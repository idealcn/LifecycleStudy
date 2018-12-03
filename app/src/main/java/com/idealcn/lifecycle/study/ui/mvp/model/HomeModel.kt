package com.idealcn.lifecycle.study.ui.mvp.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.idealcn.lifecycle.study.AppApplication
import com.idealcn.lifecycle.study.bean.BaseResponseBean
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import com.idealcn.lifecycle.study.bean.ResponseLoginBean
import com.idealcn.lifecycle.study.exception.BaseThrowable
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.Api
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.transformer.RxErrorHandler
import io.reactivex.Observable
import org.xml.sax.ErrorHandler

/**
 * author:guoning
 * date: 2018/12/2 11:56
 * 描述:
 */
open class HomeModel { //public constructor( application: Application) : AndroidViewModel(application) {

//    init {
//        application = AppApplication.instance
//    }

    fun loadArticleList(page: Int) : Observable<BaseResponseBean<HomeArticleBean>> {
        return RetrofitClient.newInstance().api.articleList(page)
    }

    //如果对应页面有多种类型数据,这里就可以定义多个不同泛型类型的LiveData
//     val liveData : MutableLiveData<HomeArticleBean> = MutableLiveData<HomeArticleBean>()

//    fun  getArticleList(page: Int) : LiveData<HomeArticleBean> {
//        val observable = loadArticleList(page).ext().compose(RxErrorHandler.handlerError((getApplication() as AppApplication).applicationContext))
//        observable.subscribe({
//            val errorCode = it.errorCode
//            when(errorCode) {
//                Api.ErrorCode.CODE_0 -> {
//                    liveData.postValue(it.data)
//                }
//                else -> {
//
//                }
//            }
//        },{
//
//        },{
//
//        })
//        return liveData
//    }
}