package com.idealcn.lifecycle.study.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.idealcn.lifecycle.study.AppHelper
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.ext.gotoAndFinishActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit

/**
 * @author: guoning
 * @date: 2018/11/29 9:51
 * @description:
 */
class SplashActivity : AppCompatActivity() {


    private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var count = 5
        compositeDisposable.add(
            RxView.clicks(btnTimer).throttleFirst(300,TimeUnit.MILLISECONDS)
                .doOnSubscribe {
                    count = btnTimer.text.toString().trim().toInt()
                }
                .map {
                    //倒计时未结束
                    count>0
                }
                .flatMap {
                    if (it){
                        Observable.just(it)
                    }else {
                        Observable.empty()
                    }
                }
                .map {
                    //倒计时和登录状态
                    it && AppHelper.getHelper().hasLogin()
                }
                .subscribe( {
                    if (it){
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    }else{
                        startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                    }
                },{

                },{

                })
        )


        val index =3L
        compositeDisposable.add(
            //倒计时
            Flowable.intervalRange(0,index,1,1,TimeUnit.SECONDS)
                .map {
                    index - it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .filter {
                    btnTimer.text = "$it"
                    it.toInt() == 1
                }
                .map {
                   AppHelper.getHelper().hasLogin()
                }
                .subscribe {
                   if (it)
                   {
                       gotoAndFinishActivity(MainActivity::class.java)
                   }
                    else
                   {
                       gotoAndFinishActivity(LoginActivity::class.java)
                   }
                }
        )
    }

}