package com.idealcn.lifecycle.study.dagger.component

import com.idealcn.lifecycle.study.dagger.module.RegisterModule
import com.idealcn.lifecycle.study.ui.activity.RegisterActivity
import dagger.Component

/**
 * author:guoning
 * date: 2018/11/29 22:10
 * 描述:
 */
@Component(modules = [RegisterModule::class])
interface RegisterComponent {
    fun inject(activity: RegisterActivity)
}