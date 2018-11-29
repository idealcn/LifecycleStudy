package com.idealcn.lifecycle.study.transformer

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.idealcn.lifecycle.study.bean.BaseResponseBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.json.JSONException

object RxErrorHandler {
      fun <T> handlerError(context: Context) : GlobalErrorTransformer<BaseResponseBean<T>> = GlobalErrorTransformer(

          upStreamSchedulerProvider = {AndroidSchedulers.mainThread()},

          downStreamSchedulerProvider = {AndroidSchedulers.mainThread()},

          globalDoOnErrorConsumer = { error ->
                when (error) {
                    is JSONException -> {
                        Toast.makeText(context, "全局异常捕获-Json解析异常！", Toast.LENGTH_SHORT).show()
                    }
                    else -> {

                    }
                }
          },
          retryConfigProvider = { error ->
              when(error){
                  is ConnectFailedAlertDialogException -> RetryConfig{
                    RxDialog.showErrorDialog(context,"ConnectionException")
                  }
//                  is TokenExpiredException -> RetryConfig(delay = 3000){
//
//                  }
                  else -> RetryConfig()
              }

          },
          globalOnNextInterceptor = { bean ->
              Observable.just(bean)
          },
          //拦截错误
          globalOnErrorResume = { throwable ->
             Observable.empty<BaseResponseBean<T>>()
          }


      )


}