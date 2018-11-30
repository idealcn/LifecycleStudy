package com.idealcn.lifecycle.study

import com.idealcn.lifecycle.study.room.AppRoomDatabase

object AppHelper {


    fun hasLogin() : Boolean{


        return false
    }


    fun getDatabase() : AppRoomDatabase {
        return AppApplication.getDatabase()
    }
}