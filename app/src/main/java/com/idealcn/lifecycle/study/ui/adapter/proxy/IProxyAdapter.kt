package com.idealcn.lifecycle.study.ui.adapter.proxy

import android.view.ViewGroup
import com.idealcn.lifecycle.study.ui.adapter.AbstractBaseHolder

/**
 * @author: guoning
 * @date: 2018/12/7 13:45
 * @description: Adapter代理接口,用于处理多种type布局
 */
interface IProxyAdapter<T> {

    /**
     * 用于区分不同的布局
     */
    fun  canParseViewType(data: T): Boolean
    fun  onBindViewHolder(holder: AbstractBaseHolder, layoutPosition: Int, data: T)
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseHolder

}