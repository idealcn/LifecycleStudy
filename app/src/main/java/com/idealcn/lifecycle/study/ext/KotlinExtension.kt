package com.idealcn.lifecycle.study.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.idealcn.lifecycle.study.AppApplication
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.reflect.KClass

fun <T>  Single<T>.ext() : Single<T>{
   return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun  <T> Observable<T>.ext() : Observable<T>{
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.ext() : Flowable<T>{
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


fun  String.notNull(str : String) :String{
    return if (TextUtils.isEmpty(str)) "" else str
}



fun <T : Activity> Activity.goto(clazz : Class<T>){
    this.startActivity(Intent(this,clazz))
}


fun <T : Activity> Activity.gotoAndFinishActivity(clazz : Class<T>){
    this.startActivity(Intent(this,clazz))
    this .finish()
}


fun Toast.show(context: Context,msg :String){
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}