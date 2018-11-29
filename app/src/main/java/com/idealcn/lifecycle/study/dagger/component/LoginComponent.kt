package com.idealcn.lifecycle.study.dagger.component

import com.idealcn.lifecycle.study.dagger.module.LoginModule
import com.idealcn.lifecycle.study.ui.activity.LoginActivity
import dagger.Component

/**
 * author:guoning
 * date: 2018/11/29 23:07
 * 描述:
 */
@Component(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(activity: LoginActivity)
}