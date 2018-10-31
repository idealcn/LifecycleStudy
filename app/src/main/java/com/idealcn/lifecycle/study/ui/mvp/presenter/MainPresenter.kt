package com.idealcn.lifecycle.study.ui.mvp.presenter

import android.arch.lifecycle.ViewModelProviders
import com.idealcn.lifecycle.study.ui.mvp.view.MainView

class MainPresenter {


    private lateinit var view: MainView


    fun attach(view : MainView){
        this.view = view
    }

    fun homeArticleList(page : Int){

    }

}