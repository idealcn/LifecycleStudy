package com.idealcn.lifecycle.study.ui.mvp.model

import android.arch.lifecycle.ViewModel
import com.idealcn.lifecycle.study.bean.AppUser

class AppUserModel : ViewModel() {

    private var appUser : AppUser = getAppUser()

    fun getAppUser() : AppUser{

        val appUser : AppUser = AppUser("name","",
            "","")
        return appUser

    }

    fun setAppUser(appUser: AppUser){
        this.appUser = appUser
    }

}