package com.idealcn.lifecycle.study

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.idealcn.lifecycle.study.room.AppRoomDatabase

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



       fun getDatabase() : AppRoomDatabase{
           return Room.databaseBuilder(this.getAppContext(),AppRoomDatabase::class.java,"wanandroid.db").build()
       }
   }
}