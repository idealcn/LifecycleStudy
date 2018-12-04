package com.idealcn.lifecycle.study.transformer.java;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 *
 */
public abstract class RxErrorTransformer<T> implements ObservableTransformer {


    @Override
    public ObservableSource<T> apply(Observable upstream) {

        return upstream.flatMap((Function<T, Observable<T>>) t -> Observable.just(t))
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        globalDoOnError(throwable);
                    }
                }).onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(Throwable throwable) throws Exception {

                        return Observable.error(throwable);
                    }
                });
    }

    protected abstract void globalDoOnError(Throwable throwable);
}
