package com.idealcn.lifecycle.study.ui.mvp.model

import com.idealcn.lifecycle.study.bean.BaseResponseBean
import com.idealcn.lifecycle.study.bean.HomeArticleBean
import com.idealcn.lifecycle.study.bean.ResponseLoginBean
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.transformer.RxErrorHandler
import io.reactivex.Observable
import org.xml.sax.ErrorHandler

/**
 * author:guoning
 * date: 2018/12/2 11:56
 * 描述:
 */
class HomeModel {

    fun loadArticleList(page: Int) : Observable<BaseResponseBean<HomeArticleBean>> {
        return RetrofitClient.newInstance().api.articleList(page)
    }


}