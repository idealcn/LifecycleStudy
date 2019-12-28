package com.idealcn.lifecycle.study.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.base.BaseActivity
import com.idealcn.lifecycle.study.dagger.component.DaggerRegisterComponent
import com.idealcn.lifecycle.study.ext.gotoAndFinishActivity
import com.idealcn.lifecycle.study.transformer.RxDialog
import com.idealcn.lifecycle.study.ui.mvp.presenter.RegisterPresenter
import com.idealcn.lifecycle.study.ui.mvp.view.RegisterView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_register.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RegisterActivity : BaseActivity() , RegisterView {

    @Inject
    lateinit var presenter: RegisterPresenter

    private val compositeDisposable  = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerRegisterComponent.builder().build().inject(this)

        presenter.attach(this)

       compositeDisposable.add(
           RxView.clicks(btnRegister).throttleFirst(500,TimeUnit.MILLISECONDS)
           .subscribe {
               register()
           }
       )


    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun getLayout(): Int = R.layout.activity_register


    override fun showLoadingView() {
        RxDialog.showProgressDialog(this,"请求中")
    }

    private fun register(){
       val name = username.text.toString().trim()
        val  pwd = password.text.toString().trim()
        presenter.register(name,pwd,pwd)
    }

    override fun gotoActivity() {
        gotoAndFinishActivity(MainActivity::class.java)
    }


    override fun showToast(msg: String) {
       toast(msg)
    }


}