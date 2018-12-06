package com.idealcn.lifecycle.study.ui.mvp.view

import com.idealcn.lifecycle.study.bean.ChapterHistory
import com.idealcn.lifecycle.study.ui.mvp.contract.ChapterContract

/**
 * @author: guoning
 * @date: 2018/12/6 16:07
 * @description:
 */
interface ChapterView : ChapterContract.View{
    fun showRequestResult(history: ChapterHistory)
    fun showRequestProgress(msg: String)
    fun showRequestSuccess(msg: String)
}