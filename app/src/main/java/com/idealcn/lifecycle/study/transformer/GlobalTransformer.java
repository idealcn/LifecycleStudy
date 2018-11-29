package com.idealcn.lifecycle.study.transformer;

import io.reactivex.*;
import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;

public abstract class GlobalTransformer<U,D> implements ObservableTransformer<U,D>,
        SingleTransformer<U,D>,
            FlowableTransformer<U,D>,
MaybeTransformer<U,D>,CompletableTransformer{




    public GlobalTransformer(){

    }

    @Override
    public ObservableSource<D> apply(Observable<U> upstream) {
        Observable<U> uObservable = upstream.flatMap(new Function<U, ObservableSource<U>>() {
            @Override
            public ObservableSource<U> apply(U u) throws Exception {
                return globalIntercept(u);
            }
        });
        uObservable.onErrorResumeNext(new ObservableSource<U>() {
            @Override
            public void subscribe(Observer<? super U> observer) {
                observer.onComplete();
            }
        });
        return null;
    }

    protected abstract ObservableSource<U> globalIntercept(U u);

    @Override
    public SingleSource<D> apply(Single<U> upstream) {

        return null;
    }

    @Override
    public Publisher<D> apply(Flowable<U> upstream) {
        return null;
    }

    @Override
    public MaybeSource<D> apply(Maybe<U> upstream) {
        return null;
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return null;
    }
}
