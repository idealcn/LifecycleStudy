package com.idealcn.lifecycle.study.dagger.component

import com.idealcn.lifecycle.study.dagger.module.MainModule
import com.idealcn.lifecycle.study.ui.MainActivity
import dagger.Component

/**
 * @author: guoning
 * @date: 2018/11/30 17:02
 * @description:
 */
@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}