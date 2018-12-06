package com.idealcn.lifecycle.study.ui.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

 class AbstractBaseHolder<T : ViewDataBinding>(var  dataBinding: T) : RecyclerView.ViewHolder(dataBinding.root) {
}