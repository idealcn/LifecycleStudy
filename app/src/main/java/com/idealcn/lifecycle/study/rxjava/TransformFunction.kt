package com.idealcn.lifecycle.study.rxjava

import com.idealcn.lifecycle.study.bean.BaseResponseBean
import com.idealcn.lifecycle.study.exception.BaseThrowable
import io.reactivex.functions.Function

class TransformFunction<T> : Function<BaseResponseBean<T>,T> {
    override fun apply(t: BaseResponseBean<T>): T {
        if (t.errorCode == 0)
            return t.data
       throw BaseThrowable(t.errorMsg,t.errorCode)
    }


}