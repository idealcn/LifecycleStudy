package com.idealcn.lifecycle.study.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.ext.goto

class LoginActivity : AppCompatActivity() {

    companion object {
      const  val EXTRA_SUCCESS = ""
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }


    fun register(view: View){
        goto(RegisterActivity::class.java)
    }
}
