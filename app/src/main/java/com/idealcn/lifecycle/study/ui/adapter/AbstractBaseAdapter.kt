package com.idealcn.lifecycle.study.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idealcn.lifecycle.study.bean.ChapterHistory
import com.idealcn.lifecycle.study.ui.adapter.proxy.IProxyAdapter
import java.lang.RuntimeException

abstract class AbstractBaseAdapter<T> : RecyclerView.Adapter<AbstractBaseHolder> {


    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_FOOTER = 1
        const val TYPE_NORMAL = 2
    }

    private var mHeaderView : View? = null
    private var mFooterView : View? = null

    private  var dataList :ArrayList<T>

    private val proxyAdapterList = mutableListOf<IProxyAdapter<T>>()

    constructor(){
        dataList = arrayListOf()
    }

    constructor ( list : ArrayList<T>){
        dataList = list
    }

    override fun onBindViewHolder(holder: AbstractBaseHolder, position: Int) {
//        val type = getItemViewType(position)
//        when(type){
//            TYPE_HEADER -> {
//
//            }
//            TYPE_FOOTER -> {
//
//            }
//            TYPE_NORMAL -> {
//
//                onBindNormalHolder(holder,holder.layoutPosition,dataList[holder.layoutPosition])
//            }
//            else -> {
//                val proxy = proxyAdapterList[getItemViewType(position)]
//                proxy.onBindViewHolder(holder,holder.layoutPosition,dataList[holder.layoutPosition])
//            }
//        }

        val itemViewType = holder.itemViewType
        val iProxyAdapter = proxyAdapterList[itemViewType]
        iProxyAdapter.onBindViewHolder(holder,holder.layoutPosition,dataList[holder.layoutPosition])

    }

//    abstract fun onBindNormalHolder(
//        holder: AbstractBaseHolder,
//        position: Int,
//        t: T
//    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseHolder {


//       if (proxyAdapterList.isNotEmpty()){
//            val iProxyAdapter :IProxyAdapter<T>? = proxyAdapterList[viewType]
//            iProxyAdapter?.run {
//                return onCreateViewHolder(parent,viewType)
//            }
//        }

        val iProxyAdapter = proxyAdapterList[viewType]
        val viewHolder = iProxyAdapter.onCreateViewHolder(parent, viewType)
        return viewHolder


//        val context = parent.context
//        val holder :AbstractBaseHolder
//         holder = AbstractBaseHolder(LayoutInflater.from(context).inflate(getLayout(),parent,false))
//        return holder
    }

    override fun getItemCount(): Int {
        var size = dataList.size
        if (getHeaderView()!=null)size += 1
        if (getFooterView()!=null)size += 1
        return  size
    }

    override fun getItemViewType(position: Int): Int {


//        if (getHeaderView()!=null && position==0)return TYPE_HEADER
//        if (getFooterView()!=null && position == itemCount-1)return TYPE_FOOTER

        val data = dataList[position]
        proxyAdapterList.forEach{ proxy ->
            if (proxy.canParseViewType(data)){
                return proxyAdapterList.indexOf(proxy)
            }
        }
        throw RuntimeException("未找到对应的代理adapter,你需要先添加IAdapterProxyImpl去处理对应的布局")
    }

    /**
     * 返回布局id
     */
//    abstract fun getLayout() : Int


    fun addProxyAdapter(proxy : IProxyAdapter<T>){
            proxyAdapterList.add(proxy)
    }

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
        val size = dataList.size
//        if (size>0){
            dataList.addAll(size,data)
//        }else
//            dataList.addAll(0,data)
        notifyItemRangeChanged(size,data.size)
    }

    fun addData( index : Int,data : ArrayList<T> ){
        dataList.addAll(index,data)
    }

    fun getData() : ArrayList<T>{
        return dataList
    }

    fun clearData() {
        dataList.clear()
        //这一步是考虑到多个tab共用一个RecyclerView,如果只是清空数据集合,
        // 而不刷新RecyclerView,新加载的数据大小与之前不一致会导致IndexOutOfBound异常
        notifyDataSetChanged()
    }


}