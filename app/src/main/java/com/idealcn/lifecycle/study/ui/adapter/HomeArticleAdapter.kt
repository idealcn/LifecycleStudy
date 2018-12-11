package com.idealcn.lifecycle.study.ui.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.Article
import com.idealcn.lifecycle.study.databinding.AdapterHomeArticleBinding

class HomeArticleAdapter constructor(  context: Context) : RecyclerView.Adapter<HomeArticleAdapter.ArticleHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    private var articleList: MutableList<Article> = mutableListOf()

//    private  var listener : OnAdapterItemClickListener? = null

//    interface OnAdapterItemClickListener{
//        fun onItemClick(position: Int)
//    }
//
//     fun setOnAdapterItemClickListener(listener: OnAdapterItemClickListener){
//        this.listener = listener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val binding =
            DataBindingUtil.inflate<AdapterHomeArticleBinding>(inflater, R.layout.adapter_home_article, parent, false)
        return ArticleHolder(binding)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {

        val adapterPosition = holder.adapterPosition
        if (adapterPosition==RecyclerView.NO_POSITION)return

        val binding = holder.binding
        val article = articleList[holder.layoutPosition]

        binding.article = article

//        holder.author.text = article.author
//        holder.chapterName.text = article.chapterName
//        holder.itemView.tag = adapterPosition
//        holder.itemView.setOnClickListener {
//            val tag = holder.itemView.tag
//            if (tag != adapterPosition) return@setOnClickListener
//            listener?.onItemClick(adapterPosition)
//        }
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

    inner class ArticleHolder(var binding: AdapterHomeArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        val chapterName: TextView = binding.chapterName//itemView.findViewById(R.id.chapterName)
        val author: TextView = binding.author//itemView.findViewById(R.id.author)
    }
}