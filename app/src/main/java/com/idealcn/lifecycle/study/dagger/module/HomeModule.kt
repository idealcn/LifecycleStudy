package com.idealcn.lifecycle.study.dagger.module

import com.idealcn.lifecycle.study.ui.mvp.model.MainViewModel
import dagger.Module
import dagger.Provides

/**
 * author:guoning
 * date: 2018/12/2 11:38
 * 描述:
 */
@Module
class HomeModule {
    @Provides
    fun provideMainViewModel(model: MainViewModel) : MainViewModel = model
}