package com.idealcn.lifecycle.study

import android.app.Application
import android.content.Context

class AppApplication : Application() {




    override fun onCreate() {
        super.onCreate()
       instance =  applicationContext
    }

   companion object {
       lateinit  var instance : Context
       fun getAppContext() : Context {
           return  instance
       }
   }
}