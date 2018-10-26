package com.idealcn.lifecycle.study.bean

data class BaseResponseBean<T>(var errorCode:Int,var errorMsg:String,var data : T)