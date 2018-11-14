package com.idealcn.lifecycle.study.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.AppUser
import com.idealcn.lifecycle.study.rxjava.RxSingleObserver
import com.idealcn.lifecycle.study.ui.mvp.model.AppUserModel
import com.idealcn.lifecycle.study.ui.mvp.model.RegisterModel
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_register.*
import org.reactivestreams.Subscriber

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val model = ViewModelProviders.of(this).get(RegisterModel::class.java)
        val appUserModel = ViewModelProviders.of(this).get(AppUserModel::class.java)


        btnRegister.setOnClickListener {
            val username = username.text.toString().trim()
           if (TextUtils.isEmpty(username))
               return@setOnClickListener
            val password = password.text.toString().trim()
            if (TextUtils.isEmpty(password))
                return@setOnClickListener


            //去注册
            model.register(username,password,password)
                .subscribeWith(RxSingleObserver<AppUser>(object  : RxSingleObserver.Companion.RxCallback<AppUser>{
                    override fun success(data: AppUser) {

                    }

                }))
        }
    }

}