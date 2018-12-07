package com.idealcn.lifecycle.study.ui.adapter.proxy.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.ChapterHistory
import com.idealcn.lifecycle.study.ui.adapter.AbstractBaseHolder
import com.idealcn.lifecycle.study.ui.adapter.proxy.IProxyAdapter
import kotlinx.android.synthetic.main.adapter_fresh_chapter.view.*

/**
 * @author: guoning
 * @date: 2018/12/7 13:46
 * @description: 最新的公众号文章
 */
class FreshAdapterProxy : IProxyAdapter<ChapterHistory.ChapterHistoryChild> {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseHolder {

        val root = LayoutInflater.from(parent.context).inflate(R.layout.adapter_fresh_chapter, parent, false)
        return AbstractBaseHolder(root)
    }

    override fun  canParseViewType(data: ChapterHistory.ChapterHistoryChild): Boolean {
        //这个条件用于区分不同的布局
        return data.fresh
    }

    override fun  onBindViewHolder(holder: AbstractBaseHolder, layoutPosition: Int, data: ChapterHistory.ChapterHistoryChild) {
        holder.root.author.text = data.author
        holder.root.chapterTitle.text = data.title
    }
}