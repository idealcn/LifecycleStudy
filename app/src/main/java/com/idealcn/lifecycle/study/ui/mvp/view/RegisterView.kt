package com.idealcn.lifecycle.study.ui.mvp.view

/**
 * author:guoning
 * date: 2018/11/29 22:21
 * 描述:
 */
interface RegisterView {
    fun showToast(msg:String)
    fun gotoActivity()
    fun showLoadingView()
}