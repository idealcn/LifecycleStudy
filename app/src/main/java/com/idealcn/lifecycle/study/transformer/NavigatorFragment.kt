package com.idealcn.lifecycle.study.transformer

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.idealcn.lifecycle.study.ui.activity.LoginActivity
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class NavigatorFragment : Fragment() {

    private lateinit var resultSubject: PublishSubject<Boolean>
    private lateinit var cancelSubject: PublishSubject<Boolean>
    private val attachSubject = PublishSubject.create<Boolean>()

    fun startLoginForResult(activity: FragmentActivity): Single<Boolean> {
        val fragmentManager = activity.supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(TAG)

        return if (fragment == null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(this, TAG).commitAllowingStateLoss()
            this.startLoginSingle()
        } else {
            val navigatorFragment = fragment as NavigatorFragment
            navigatorFragment.startLoginSingle()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        attachSubject.onNext(true)
        attachSubject.onComplete()
    }
    private fun startLoginSingle(): Single<Boolean> {
        resultSubject = PublishSubject.create()
        cancelSubject = PublishSubject.create()
        startLogin()
        return resultSubject
                .takeUntil(cancelSubject)
                .single(false)
    }
    private fun startLogin() {
        if (!isAdded) {
            attachSubject.subscribe { _ -> startLoginForResult() }
        } else {
            startLoginForResult()
        }
    }
    private fun startLoginForResult() {
        startActivityForResult(Intent(context, LoginActivity::class.java), 1)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val loginSuccess = data.getBooleanExtra(LoginActivity.EXTRA_SUCCESS, false)
            resultSubject.onNext(loginSuccess)
            resultSubject.onComplete()
        } else {
            cancelSubject.onNext(true)
        }
    }
    companion object {
        const val TAG = "NavigatorFragment"
    }
}