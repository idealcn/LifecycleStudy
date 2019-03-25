package com.idealcn.lifecycle.study.base.mvp;

import io.reactivex.disposables.CompositeDisposable;

import java.lang.ref.WeakReference;

public interface MVPBaseContract {


    public abstract class MVPBasePresenter<V extends MVPBaseView> {

        protected WeakReference<V> reference;
        protected CompositeDisposable compositeDisposable = new CompositeDisposable();

        public   void attach(V view){
            reference = new WeakReference<>(view);
        }

        public void detach(){
            reference.clear();
            compositeDisposable.clear();
        }
    }


    public interface MVPBaseView {

        void showRequestProgress();

        void hideRequestProgress();

        <T>  void showRequestResult(T result);

        void showRequestError(String error);
    }
}
