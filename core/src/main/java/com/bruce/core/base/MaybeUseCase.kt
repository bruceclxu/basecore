package com.bruce.core.base


import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.ResourceMaybeObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by bruce
 */
abstract class MaybeUseCase<Params, T> {
    private var scheduler = Schedulers.io()
    private var schedulerObserve = AndroidSchedulers.mainThread()
    /**
     * Builds an [Observable] which will be used when executing the current [MaybeUseCase].
     */
    abstract fun buildUseCaseObservable(params: Params?): Maybe<T>? 
    
    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observable build
     * by [.buildUseCaseObservable] ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(observer: ResourceMaybeObserver<T>?, params: Params?=null): ResourceMaybeObserver<T>? {
        var maybe =  buildUseCaseObservable(params)
                ?.subscribeOn(scheduler)
                ?.observeOn(schedulerObserve)
        return maybe?.subscribeWith(observer)
    }
    
}

