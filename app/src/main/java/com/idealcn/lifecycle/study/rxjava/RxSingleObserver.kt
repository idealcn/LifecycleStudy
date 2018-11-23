package com.idealcn.lifecycle.study.rxjava

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class RxSingleObserver<T> constructor(var callback : RxCallback<T>) : SingleObserver<T> {

    override fun onSuccess(t: T) {
        callback.success(t)
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onError(e: Throwable) {

    }

    companion object {
        open interface RxCallback<T>{
            fun success(data : T)
        }
    }
}