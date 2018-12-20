package com.idealcn.lifecycle.study;

import android.app.Application;
import com.idealcn.lifecycle.study.greendao.source.DaoMaster;
import com.idealcn.lifecycle.study.greendao.source.DaoSession;

public class AppApplication extends Application {

    private DaoSession daoSession;

    private static AppApplication application;


    public static AppApplication get(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        daoSession =  DaoMaster.newDevSession(this,"wanandroid.db");
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
