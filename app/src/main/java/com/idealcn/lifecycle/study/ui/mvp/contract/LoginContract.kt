package com.idealcn.lifecycle.study.ui.mvp.contract

import java.lang.ref.WeakReference

/**
 * author:guoning
 * date: 2018/11/29 23:10
 * 描述:
 */
interface LoginContract {
    interface Presenter<V : View> : BaseContract.BasePresenter<V> {


        fun login(username: String?, password: String?)


    }


    interface View :BaseContract.BaseView {
        fun showToast(msg: String)
        fun gotoActivity()
        fun showLoadingView()

    }
}