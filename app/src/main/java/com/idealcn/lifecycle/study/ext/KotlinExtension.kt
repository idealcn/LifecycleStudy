package com.idealcn.lifecycle.study.ext

import android.text.TextUtils
import android.widget.Toast
import com.idealcn.lifecycle.study.AppApplication
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T>  Single<T>.ext() : Single<T>{
   return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun  <T> Observable<T>.ext() : Observable<T>{
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


fun  String.notNull(str : String) :String{
    return if (TextUtils.isEmpty(str)) "" else str
}


