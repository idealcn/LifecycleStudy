package com.idealcn.lifecycle.study.transformer

import com.idealcn.lifecycle.study.bean.BaseResponseBean
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author: guoning
 * @date: 2018/11/14 17:24
 * @description:
 */
class GlobalErrorTransformer<T> constructor(
    private val globalOnNextInterceptor: (T) -> Observable<T> = { Observable.just(it) },
    private val globalOnErrorResume: (Throwable) -> Observable<T> = { Observable.error(it) },
    private val retryConfigProvider: (Throwable) -> RetryConfig = { RetryConfig() },
    private val globalDoOnErrorConsumer: (Throwable) -> Unit = { },
    private val upStreamSchedulerProvider: () -> Scheduler = { AndroidSchedulers.mainThread() },
    private val downStreamSchedulerProvider: () -> Scheduler = { AndroidSchedulers.mainThread() }
) : ObservableTransformer<T, T>,
    FlowableTransformer<T, T>,
    SingleTransformer<T, T>,
    MaybeTransformer<T, T>,
    CompletableTransformer {

    override fun apply(upstream: Observable<T>): Observable<T> =
        upstream
            .flatMap {
                globalOnNextInterceptor(it)
            }
            .onErrorResumeNext { throwable: Throwable ->
                globalOnErrorResume(throwable)
            }
            .observeOn(upStreamSchedulerProvider())
            .retryWhen(ObservableRetryDelay(retryConfigProvider))
            .doOnError(globalDoOnErrorConsumer)
            .observeOn(downStreamSchedulerProvider())

    override fun apply(upstream: Completable): Completable =
        upstream
            .onErrorResumeNext {
                globalOnErrorResume(it).ignoreElements()
            }
            .observeOn(upStreamSchedulerProvider())
            .retryWhen(FlowableRetryDelay(retryConfigProvider))
            .doOnError(globalDoOnErrorConsumer)
            .observeOn(downStreamSchedulerProvider())

    override fun apply(upstream: Flowable<T>): Flowable<T> =
        upstream
            .flatMap {
                globalOnNextInterceptor(it)
                    .toFlowable(BackpressureStrategy.BUFFER)
            }
            .onErrorResumeNext { throwable: Throwable ->
                globalOnErrorResume(throwable)
                    .toFlowable(BackpressureStrategy.BUFFER)
            }
            .observeOn(upStreamSchedulerProvider())
            .retryWhen(FlowableRetryDelay(retryConfigProvider))
            .doOnError(globalDoOnErrorConsumer)
            .observeOn(downStreamSchedulerProvider())

    override fun apply(upstream: Maybe<T>): Maybe<T> =
        upstream
            .flatMap {
                globalOnNextInterceptor(it)
                    .firstElement()
            }
            .onErrorResumeNext { throwable: Throwable ->
                globalOnErrorResume(throwable)
                    .firstElement()
            }
            .observeOn(upStreamSchedulerProvider())
            .retryWhen(FlowableRetryDelay(retryConfigProvider))
            .doOnError(globalDoOnErrorConsumer)
            .observeOn(downStreamSchedulerProvider())

    override fun apply(upstream: Single<T>): Single<T> =
        upstream
            .flatMap {
                globalOnNextInterceptor(it)
                    .firstOrError()
            }
            .onErrorResumeNext { throwable ->
                globalOnErrorResume(throwable)
                    .firstOrError()
            }
            .observeOn(upStreamSchedulerProvider())
            .retryWhen(FlowableRetryDelay(retryConfigProvider))
            .doOnError(globalDoOnErrorConsumer)
            .observeOn(downStreamSchedulerProvider())
}
