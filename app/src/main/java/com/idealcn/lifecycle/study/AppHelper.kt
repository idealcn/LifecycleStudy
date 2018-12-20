package com.idealcn.lifecycle.study

import com.idealcn.lifecycle.study.bean.ResponseLoginBean
import com.idealcn.lifecycle.study.greendao.source.DaoSession

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


    fun getDaoSession() : DaoSession {
        return AppApplication.get().getDaoSession()
    }

    fun saveUser(user: ResponseLoginBean) {
        this.user = user
    }


}