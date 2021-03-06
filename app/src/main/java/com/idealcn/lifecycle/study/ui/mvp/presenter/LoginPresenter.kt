package com.idealcn.lifecycle.study.ui.mvp.presenter

import android.arch.lifecycle.ViewModelProviders
import android.text.TextUtils
import com.idealcn.lifecycle.study.AppHelper
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.Api
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.ui.activity.LoginActivity
import com.idealcn.lifecycle.study.ui.mvp.contract.BaseContract
import com.idealcn.lifecycle.study.ui.mvp.contract.LoginContract
import com.idealcn.lifecycle.study.ui.mvp.model.LoginModel
import com.idealcn.lifecycle.study.ui.mvp.view.LoginView
import com.idealcn.lifecycle.study.ui.mvp.view.RegisterView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * author:guoning
 * date: 2018/11/29 23:10
 * 描述:
 */
class LoginPresenter @Inject constructor() : LoginContract.Presenter<LoginView> {


    private lateinit var weakReference: WeakReference<LoginView>

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attach(view: LoginView) {
        this.weakReference = WeakReference<LoginView>(view)
    }

    override fun detach(){
        weakReference.clear()
        compositeDisposable.dispose()
    }

    override fun login(username: String?, password: String?) {

        compositeDisposable.add(
            Observable.just(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password))
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    if (it) {
                        ViewModelProviders.of(weakReference.get() as LoginActivity)
                            .get(LoginModel::class.java)
                            .login(username,password)
                            .doOnSubscribe {
                                show("登录中",1)
                            }
                    } else {
                        Observable.empty()
                    }
                }
                .subscribe({ next ->
                    val errorCode = next.errorCode
                    val errorMsg = next.errorMsg
                    val data = next.data
                    show(errorMsg,errorCode)
                    if (errorCode==Api.ErrorCode.CODE_0){
                        ViewModelProviders.of(weakReference.get() as LoginActivity)
                            .get(LoginModel::class.java)
                            .saveUser(data)
                    }
                }, { throwable ->
                    println(throwable.message)
                }, {})
        )

    }


    private fun show(msg : String,errorCode :Int) {
        val loginView : LoginView? = weakReference.get()
        loginView?.let {
            loginView.showToast(msg)
            when(errorCode){
                Api.ErrorCode.CODE_0 ->{
                    loginView.gotoActivity()
                }
                1 ->{
                   // loginView.showLoadingView()
                }
                else -> {

                }
            }
        }
    }
}