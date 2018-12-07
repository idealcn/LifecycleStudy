package com.idealcn.lifecycle.study.ui.adapter.proxy.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.ChapterHistory
import com.idealcn.lifecycle.study.ui.adapter.AbstractBaseHolder
import com.idealcn.lifecycle.study.ui.adapter.proxy.IProxyAdapter
import kotlinx.android.synthetic.main.adapter_chapter.view.*

/**
 * @author: guoning
 * @date: 2018/12/7 15:05
 * @description: 正常的布局
 */
class NormalAdapterProxy : IProxyAdapter<ChapterHistory.ChapterHistoryChild> {
    override fun canParseViewType(data: ChapterHistory.ChapterHistoryChild): Boolean {
        return  !data.fresh
    }

    override fun onBindViewHolder(
        holder: AbstractBaseHolder,
        layoutPosition: Int,
        data: ChapterHistory.ChapterHistoryChild
    ) {
        holder.root.author.text = data.author
        holder.root.chapterTitle.text = data.title
        holder.root.chapterName.text = data.chapterName
        holder.root.updateTime.text = data.niceDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseHolder {
        return  AbstractBaseHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_chapter,parent,false))
    }
}