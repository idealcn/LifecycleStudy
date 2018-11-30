package com.idealcn.lifecycle.study.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * @author: guoning
 * @date: 2018/11/30 11:18
 * @description:
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    abstract fun getLayout(): Int


    protected fun toast(msg :String) = Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
}