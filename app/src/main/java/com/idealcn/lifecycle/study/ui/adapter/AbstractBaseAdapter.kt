package com.idealcn.lifecycle.study.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class AbstractBaseAdapter<T>(var list : ArrayList<T>) : RecyclerView.Adapter<AbstractBaseHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_FOOTER = 1
        const val TYPE_NORMAL = 2
    }

    private var mHeaderView : View? = null
    private var mFooterView : View? = null

    override fun onBindViewHolder(holder: AbstractBaseHolder, position: Int) {
        val type = getItemViewType(position)
        when(type){
            TYPE_HEADER -> {

            }
            TYPE_FOOTER -> {

            }
            TYPE_NORMAL -> {
                onBindNormalHolder(holder,holder.layoutPosition)
            }
            else -> {

            }
        }

    }

    abstract fun onBindNormalHolder(holder: AbstractBaseHolder, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseHolder {
        val context = parent.context
        val holder :AbstractBaseHolder
         holder = AbstractBaseHolder(LayoutInflater.from(context).inflate(getLayout(),parent,false))
        return holder
    }

    override fun getItemCount(): Int {
        var size = list.size
        if (getHeaderView()!=null)size += 1
        if (getFooterView()!=null)size += 1
        return  size
    }

    override fun getItemViewType(position: Int): Int {
        if (getHeaderView()!=null && position==0)return TYPE_HEADER
        if (getFooterView()!=null && position == itemCount-1)return TYPE_FOOTER
        return TYPE_NORMAL
    }







    abstract fun getLayout() : Int



    fun  addHeaderView(headerView : View){
        this.mHeaderView = headerView
    }

    fun getHeaderView() :View? {
        return this.mHeaderView
    }


    fun addFooterView(footerView :View){
        this.mFooterView = footerView
    }

    fun  getFooterView() :View?{
        return this.mFooterView
    }

    fun addData(data: ArrayList<T>) {
        val size = list.size
        if (size>0){
            list.addAll(size,data)
        }else
           list.addAll(0,data)
    }

    fun addData( index : Int,data : ArrayList<T> ){
            list.addAll(index,data)
    }


}