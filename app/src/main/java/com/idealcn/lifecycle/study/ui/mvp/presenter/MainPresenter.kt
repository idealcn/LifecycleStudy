package com.idealcn.lifecycle.study.ui.mvp.presenter

import android.arch.lifecycle.ViewModelProviders
import com.idealcn.lifecycle.study.ui.mvp.contract.MainContract
import com.idealcn.lifecycle.study.ui.mvp.view.MainView
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainPresenter @Inject constructor() : MainContract.Presenter<MainView>{

    private lateinit var weakReference: WeakReference<MainView>

    override fun attach(view: MainView) {
        this.weakReference = WeakReference<MainView>(view)
    }


    fun homeArticleList(page : Int){

    }

}