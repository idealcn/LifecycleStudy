package com.idealcn.lifecycle.study.ui.mvp.model

import android.arch.lifecycle.ViewModel
import com.idealcn.lifecycle.study.bean.AppUser
import com.idealcn.lifecycle.study.exception.BaseThrowable
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.rxjava.TransformFunction
import io.reactivex.Observable
import io.reactivex.Single

class RegisterModel : ViewModel() {

    fun register(username: String, password: String, repassword: String): Single<AppUser> {
        return RetrofitClient.newInstance().api.register(username, password, repassword)
            .ext()
            .map(TransformFunction())
    }
}