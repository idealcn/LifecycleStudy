package com.idealcn.lifecycle.study.base.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 多type的adapter的基类
 * @param <T> 数据类型
 * @param <VH>  ViewHolder
 */
public  class XBKBaseAdapter<T,VH extends BaseViewHolder> extends BaseQuickAdapter<T,VH> {

    private List<IMultiTypeAdapter<T,VH>> iMultiTypeAdapterList = new ArrayList<>();

    public XBKBaseAdapter() {
        super(new ArrayList<>());
    }

    public XBKBaseAdapter(@Nullable List<T> data) {
        super(data);
    }

    @Override
    protected void convert(VH holder, T item) {
        int layoutPosition = holder.getLayoutPosition();
        IMultiTypeAdapter<T, VH> iMultiTypeAdapter = iMultiTypeAdapterList.get(getItemViewType(layoutPosition));
        iMultiTypeAdapter.onBindViewHolder(holder,layoutPosition,item);
    }


    @Override
    protected VH onCreateDefViewHolder(ViewGroup parent, int viewType) {
        IMultiTypeAdapter<T, VH> iMultiTypeAdapter = iMultiTypeAdapterList.get(viewType);
        return iMultiTypeAdapter.onCreateViewHolder(parent);
    }

    @Override
    protected int getDefItemViewType(int position) {
        T data = getData().get(position);
        for (IMultiTypeAdapter<T, VH> iMultiTypeAdapter : iMultiTypeAdapterList) {
            if (iMultiTypeAdapter.canParseItemType(data)){
                return iMultiTypeAdapterList.indexOf(iMultiTypeAdapter);
            }
        }
        throw new RuntimeException("未设置对应的adapter");
    }


    public void addMultiTypeAdapter(IMultiTypeAdapter<T,VH> iMultiTypeAdapter){
        iMultiTypeAdapterList.add(iMultiTypeAdapter);
    }

}
