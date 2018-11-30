package com.idealcn.lifecycle.study.ui.mvp.contract

/**
 * @author: guoning
 * @date: 2018/11/30 16:38
 * @description:
 */
interface MainContract {

   interface Presenter<V : View> :BaseContract.BasePresenter<V> {

   }

    interface View :BaseContract.BaseView{

    }
}