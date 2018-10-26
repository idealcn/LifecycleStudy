package com.idealcn.lifecycle.study.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.Article

class HomeArticleAdapter constructor(private var context: Context) : RecyclerView.Adapter<HomeArticleAdapter.ArticleHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    private var articleList: MutableList<Article> = mutableListOf()

    private  var listener : OnAdapterItemClickListener? = null

    interface OnAdapterItemClickListener{
        fun onItemClick(position: Int)
    }

    public fun setOnAdapterItemClickListener(listener: OnAdapterItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        return ArticleHolder(inflater.inflate(R.layout.adapter_home_article, parent, false))
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = articleList[holder.layoutPosition]
        holder.author.text = article.author
        holder.chapterName.text = article.chapterName
        holder.itemView.tag = holder.layoutPosition
        holder.itemView.setOnClickListener {
            val tag = holder.itemView.tag
            if (tag != holder.layoutPosition) return@setOnClickListener
            listener?.onItemClick(holder.layoutPosition)
        }
    }

    fun setData(index: Int, list: List<Article>?) {

        if (null == list) {
            return
        }
        articleList.addAll(index, list.toMutableList())
        notifyItemRangeInserted(index, list.size)
    }

    fun getData(): MutableList<Article> {
        return articleList
    }

    inner class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chapterName: TextView = itemView.findViewById(R.id.chapterName)
        val author: TextView = itemView.findViewById(R.id.author)
    }
}