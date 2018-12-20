package com.idealcn.lifecycle.study.transformer.java;

import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;

/**
 *
 */
public abstract class RxErrorTransformer<T> implements ObservableTransformer ,FlowableTransformer,SingleTransformer,MaybeTransformer {


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

    @Override
    public Publisher<T> apply(Flowable upstream) {
     return    upstream.flatMap((Function<T,Publisher<T>>) t ->  Flowable.just(t))
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        globalDoOnError(throwable);
                    }
                })
                .onErrorResumeNext(new Function<Throwable, Publisher<T>>() {
                    @Override
                    public Publisher<T> apply(Throwable throwable) throws Exception {
                        return Flowable.error(throwable);
                    }
                });
    }

    @Override
    public MaybeSource apply(Maybe upstream) {
        return null;
    }

    @Override
    public SingleSource apply(Single upstream) {
        return null;
    }

    protected abstract void globalDoOnError(Throwable throwable);
}
