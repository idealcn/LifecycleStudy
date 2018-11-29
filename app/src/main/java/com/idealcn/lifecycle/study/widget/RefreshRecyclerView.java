package com.idealcn.lifecycle.study.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class RefreshRecyclerView<T extends RecyclerView.Adapter> extends RecyclerView {

    private T adapter;
    private LayoutManager layoutManager;

    public RefreshRecyclerView(@NonNull Context context) {
        super(context);
        adapter = (T) getAdapter();

    }

    public RefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        adapter = (T) getAdapter();

    }

    public RefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        adapter = (T) getAdapter();

    }


    public void  addHeaderView(View headerView){
        int itemCount = adapter.getItemCount();

    }

    public void  addView(int index,View headerView){
        layoutManager.addView(headerView,index);
    }

}
