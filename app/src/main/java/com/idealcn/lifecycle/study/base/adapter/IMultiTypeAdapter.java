package com.idealcn.lifecycle.study.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


public interface IMultiTypeAdapter<T, VH extends RecyclerView.ViewHolder> {

    /**
     * 根据数据T来确定对应的Adapter
     * @param data 数据
     * @return true or  false
     */
    boolean canParseItemType(T data);

    /**
     * 在这里处理布局中的单个view的点击事件
     * @param parent
     * @return
     */
    VH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(VH holder, int position, T data);

}
