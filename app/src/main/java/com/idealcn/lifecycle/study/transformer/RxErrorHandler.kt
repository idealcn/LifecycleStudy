package com.idealcn.lifecycle.study.transformer

import android.support.v4.app.FragmentActivity
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.json.JSONException

object RxErrorHandler {
      fun <T :BaseEntity> handlerError(activity :FragmentActivity) : GlobalErrorTransformer<T> = GlobalErrorTransformer(

          upStreamSchedulerProvider = {AndroidSchedulers.mainThread()},

          downStreamSchedulerProvider = {AndroidSchedulers.mainThread()},

          globalDoOnErrorConsumer = { error ->
                when (error) {
                    is JSONException -> {
                        Toast.makeText(activity, "全局异常捕获-Json解析异常！", Toast.LENGTH_SHORT).show()
                    }
                    else -> {

                    }
                }
          },
          retryConfigProvider = { error ->
              when(error){
                  is ConnectFailedAlertDialogException -> RetryConfig{
                    RxDialog.showErrorDialog(activity,"ConnectionException")
                  }
//                  is TokenExpiredException -> RetryConfig(delay = 3000){
//
//                  }
                  else -> RetryConfig()
              }

          }


      )


}