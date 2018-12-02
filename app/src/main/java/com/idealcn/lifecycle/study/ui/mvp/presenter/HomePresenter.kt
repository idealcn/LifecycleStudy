package com.idealcn.lifecycle.study.ui.mvp.presenter

import com.idealcn.lifecycle.study.AppApplication
import com.idealcn.lifecycle.study.ext.ext
import com.idealcn.lifecycle.study.http.Api
import com.idealcn.lifecycle.study.transformer.RxErrorHandler
import com.idealcn.lifecycle.study.ui.mvp.contract.HomeContract
import com.idealcn.lifecycle.study.ui.mvp.model.HomeModel
import com.idealcn.lifecycle.study.ui.mvp.view.HomeView
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * author:guoning
 * date: 2018/12/2 11:35
 * 描述:
 */
class HomePresenter @Inject constructor() :HomeContract.Presenter<HomeView> {

    lateinit var weakReference: WeakReference<HomeView>

    private  val homeModel : HomeModel

    private val compositeDisposable : CompositeDisposable

    init {
        homeModel = HomeModel()
        compositeDisposable = CompositeDisposable()
    }

    override fun detach() {
        compositeDisposable.dispose()
        weakReference.clear()
    }
    override fun attach(view: HomeView) {
        this.weakReference = WeakReference<HomeView>(view)
    }


    override fun loadArticleList(page: Int) {
       compositeDisposable.add(
           homeModel.loadArticleList(page)
               .ext().compose(RxErrorHandler.handlerError(AppApplication.getAppContext()))
               .doOnSubscribe {
                   weakReference.get()?.showRequestProgress()
               }
               .doFinally {
                   weakReference.get()?.hideRequestProgress()
               }
               .subscribe({
                    val errorCode = it.errorCode
                   when (errorCode){
                       Api.ErrorCode.CODE_0 -> {
                           weakReference.get()?.showRequestResult(it.data)
                       }
                   }
               },{

               },{

               })
       )
    }

}