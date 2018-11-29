package com.idealcn.lifecycle.study.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.AppUser
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.rxjava.RxSingleObserver
import com.idealcn.lifecycle.study.ui.mvp.model.AppUserModel
import com.idealcn.lifecycle.study.ui.mvp.model.RegisterModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_register.*
import org.reactivestreams.Subscriber

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        btnRegister.setOnClickListener {
            var name  = ""
            var pwd = ""
            Observable.just(0)
                .doOnSubscribe {
                    name = username.text.toString().trim()
                    pwd = password.text.toString().trim()
                }
                .filter {
                    TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)
                }
           // RetrofitClient.newInstance().api.register()
        }
    }

}