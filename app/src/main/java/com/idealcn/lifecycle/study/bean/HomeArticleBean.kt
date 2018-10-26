package com.idealcn.lifecycle.study.bean

data class HomeArticleBean(var datas :ArrayList<Article>,var curPage :Int,var total:Int,var size:Int,var pageCount:Int,var over:Boolean,var offset:Int)