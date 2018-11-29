package com.idealcn.lifecycle.study.ui.mvp.presenter

import android.text.TextUtils
import com.idealcn.lifecycle.study.http.Api
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.RetrofitClient
import com.idealcn.lifecycle.study.ui.mvp.contract.RegisterContract
import com.idealcn.lifecycle.study.ui.mvp.view.RegisterView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * author:guoning
 * date: 2018/11/29 22:13
 * 描述: 注册
 */
class RegisterPresenter @Inject constructor() : RegisterContract.Presenter {

    private lateinit var rootView: WeakReference<RegisterView>

    private val compositeDisposable = CompositeDisposable()

    fun attach(view: RegisterView){
        this.rootView = WeakReference<RegisterView>(view)
    }


    fun detach(){
        rootView.clear()
        compositeDisposable.dispose()
    }

    fun register(name: String, pwd: String, pwd1: String) {
        compositeDisposable.add(
            Observable.just(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd))
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { t ->
                    if (t)
                        RetrofitClient.newInstance().api.register(name,pwd,pwd1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe {
                                show("请求中",1)
                            }
                    else {
                        val registerView : RegisterView? = rootView.get()
                        registerView?.let {
                            registerView.showToast("用户名或者密码不能为空")
                        }
                        Observable.empty()
                    }
                }
                .ext()
                // .compose(RxErrorHandler.handlerError(rootView as RegisterActivity)
                .subscribe({ next ->
                    val errorCode = next.errorCode
                    val errorMsg = next.errorMsg
                    when (errorCode) {
                        Api.ErrorCode.CODE_0 -> {
                            show(errorMsg,errorCode)
//                            TODO()

                        }
                        Api.ErrorCode.CODE_1 -> {
                            show(errorMsg,errorCode)
                        }else -> {
                            show(errorMsg,errorCode)
                        }
                    }

                },{

                },{

                })
        )
    }




    private fun show(msg : String,errorCode :Int) {
        val registerView : RegisterView? = rootView.get()
        registerView?.let {
            registerView.showToast(msg)
            when(errorCode){
                Api.ErrorCode.CODE_0 ->{
                    registerView.gotoActivity()
                }
                1 ->{
                  registerView.showLoadingView()
                }
                else -> {

                }
            }
        }
    }
}