//package com.bruce.core.base
//
//
//import androidx.lifecycle.LiveData
//import io.reactivex.Maybe
//import io.reactivex.Observable
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.observers.DisposableObserver
//import io.reactivex.observers.ResourceMaybeObserver
//import io.reactivex.schedulers.Schedulers
//
///**
// * Created by bruce
// */
//abstract class LiveDataUseCase<Params, T> {
//    private var scheduler = Schedulers.io()
//    private var schedulerObserve = AndroidSchedulers.mainThread()
//    /**
//     * Builds an [Observable] which will be used when executing the current [LiveDataUseCase].
//     */
//    abstract fun buildUseCaseObservable(params: Params?): LiveData<T>? 
//    
//    /**
//     * Executes the current use case.
//     *
//     * @param observer [DisposableObserver] which will be listening to the observable build
//     * by [.buildUseCaseObservable] ()} method.
//     * @param params Parameters (Optional) used to build/execute this use case.
//     */
//    fun execute(observer: CoreResourceMaybeObserver<T>?, params: Params?=null): ResourceMaybeObserver<T>? {
//        var maybe =  buildUseCaseObservable(params)
//                ?.subscribeOn(scheduler)
//                ?.observeOn(schedulerObserve)
//        return maybe?.subscribeWith(observer)
//    }
//    
//}
//
