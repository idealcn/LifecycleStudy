package com.idealcn.lifecycle.study.ui.dagger2.component

import com.idealcn.lifecycle.study.ui.dagger2.module.MainModule
import com.idealcn.lifecycle.study.ui.fragment.MainFragment
import dagger.Component

@Component(modules = [MainModule::class])
interface MainComponent {
    fun injectFragment(fragment : MainFragment)
}