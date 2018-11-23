package com.idealcn.lifecycle.study

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.idealcn.lifecycle.study.ui.MainActivity
import java.util.logging.Logger
import javax.inject.Inject

/**
 * @author: guoning
 * @date: 2018/11/23 14:43
 * @description: 观察Activity生命周期变化
 */
class ActivityLifecycleObserver : LifecycleObserver {

    val logger = Logger.getLogger(this.javaClass.simpleName)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
   fun onCreate(owner: LifecycleOwner){
        logger.info("---------onCreate----------${owner::class.java.simpleName}")
        owner as MainActivity

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        logger.info("---------onStart----------")

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        logger.info("---------onResume----------")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        logger.info("---------onPause----------")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        logger.info("---------onStop----------")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        logger.info("---------onDestroy----------")

    }
}