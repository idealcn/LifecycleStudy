package com.idealcn.lifecycle.study.dagger.component

import com.idealcn.lifecycle.study.dagger.module.HomeModule
import com.idealcn.lifecycle.study.ui.fragment.MainFragment
import dagger.Component

/**
 * author:guoning
 * date: 2018/12/2 11:38
 * 描述:
 */
@Component(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(fragment: MainFragment)

}