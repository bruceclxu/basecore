package com.bruce.core.base

import io.reactivex.observers.ResourceMaybeObserver

/***
 * 网络异常统一处理，可以进行网络访问
 * 异常统一在这里进行处理
 */
abstract class CoreResourceMaybeObserver<T> : ResourceMaybeObserver<T>(){
    override fun onSuccess(t: T) {

    }

    override fun onComplete() {

    }

    override fun onError(e: Throwable) {

    }

}