package com.idealcn.lifecycle.study.ui.mvp.contract

/**
 * @author: guoning
 * @date: 2018/12/6 16:07
 * @description:
 */
interface ChapterContract {

    interface View : BaseContract.BaseView{

    }

    interface Presenter<V :View> : BaseContract.BasePresenter<V>{

    }

}