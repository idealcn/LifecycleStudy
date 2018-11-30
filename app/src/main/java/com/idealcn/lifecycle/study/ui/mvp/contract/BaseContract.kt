package com.idealcn.lifecycle.study.ui.mvp.contract

/**
 * author:guoning
 * date: 2018/11/29 23:32
 * 描述:
 */
interface BaseContract {
    interface BasePresenter<V : BaseView>{

        fun  attach(view : V)
    }


    interface BaseView{

    }
}