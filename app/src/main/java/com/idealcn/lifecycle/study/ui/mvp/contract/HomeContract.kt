package com.idealcn.lifecycle.study.ui.mvp.contract

import com.idealcn.lifecycle.study.bean.HomeArticleBean

/**
 * author:guoning
 * date: 2018/12/2 11:33
 * 描述:
 */
interface HomeContract {
    interface Presenter<V : View> :BaseContract.BasePresenter<V> {
        fun loadArticleList(page: Int)

    }

    interface View : BaseContract.BaseView {
        fun showRequestProgress()
        fun hideRequestProgress()
        fun showRequestResult(data: HomeArticleBean)
        fun showRequestError(errorCode: Int, errorMsg: String)
    }
}