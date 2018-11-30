package com.idealcn.lifecycle.study

import com.idealcn.lifecycle.study.bean.ResponseLoginBean
import com.idealcn.lifecycle.study.room.AppRoomDatabase

class AppHelper {

    var user : ResponseLoginBean? = null

    companion object {
        fun getHelper() : AppHelper{
            return AppHelper()
        }
    }

    fun hasLogin() : Boolean{
        return user == null
    }


    fun getDatabase() : AppRoomDatabase {
        return AppApplication.getDatabase()
    }

    fun saveUser(user: ResponseLoginBean) {
        this.user = user
    }


}