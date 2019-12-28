package com.idealcn.lifecycle.study.ui.activity

import android.os.Bundle
import android.view.View
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.base.BaseActivity
import com.idealcn.lifecycle.study.dagger.component.DaggerLoginComponent
import com.idealcn.lifecycle.study.ext.goto
import com.idealcn.lifecycle.study.ext.gotoAndFinishActivity
import com.idealcn.lifecycle.study.ext.throttled
import com.idealcn.lifecycle.study.ui.mvp.presenter.LoginPresenter
import com.idealcn.lifecycle.study.ui.mvp.view.LoginView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(),LoginView {
    override fun getLayout(): Int {
        return R.layout.activity_login
    }


    companion object {
      const  val EXTRA_SUCCESS = ""
    }

    @Inject
     lateinit var presenter : LoginPresenter

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
            DaggerLoginComponent.builder().build().inject(this)

        presenter.attach(this)

        compositeDisposable.add(
            RxView.clicks(login).throttled().subscribe {
                val username = username.text.toString().trim()
                val password = password.text.toString().trim()
              //  presenter.login(username,password)
                gotoAndFinishActivity(MainActivity::class.java)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
        compositeDisposable.dispose()
    }

    override fun showToast(msg: String) {
        toast(msg)
    }

    override fun gotoActivity() {
        gotoAndFinishActivity(MainActivity::class.java)
    }

    override fun showLoadingView() {
       // toast("请求中...")
    }


    fun register(view: View){
        goto(RegisterActivity::class.java)
    }
}
