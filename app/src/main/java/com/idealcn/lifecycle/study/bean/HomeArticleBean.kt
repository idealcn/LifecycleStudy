package com.idealcn.lifecycle.study.bean

import java.io.Serializable

data class HomeArticleBean(var datas :ArrayList<Article>,
                           var curPage :Int,
                           var total:Int,
                           var size:Int,
                           var pageCount:Int,
                           var over:Boolean,
                           var offset:Int) : Serializable