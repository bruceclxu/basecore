package com.bruce.core.rxbus

import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Tony Shen on 2017/6/14.
 */
class RxBus private constructor() {
    private var bus: Relay<Any>? = null
    private val mStickyEventMap: MutableMap<Class<*>, Any>
    fun post(event: Any) {
        bus!!.accept(event)
    }

    fun postSticky(event: Any) {
        synchronized(mStickyEventMap) { mStickyEventMap.put(event.javaClass, event) }
        bus!!.accept(event)
    }

    fun <T> toObservable(eventType: Class<T>?): Observable<T> {
        return bus!!.ofType(eventType)
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    fun <T> toObservableSticky(eventType: Class<T>): Observable<T> {
        synchronized(mStickyEventMap) {
            val observable = bus!!.ofType(eventType)
            val event = mStickyEventMap[eventType]
            return if (event != null) {
                observable.mergeWith(Observable.create { e -> e.onNext(eventType.cast(event)) })
            } else {
                observable
            }
        }
    }

    fun hasObservers(): Boolean {
        return bus!!.hasObservers()
    }
    
    fun <T> register(eventType: Class<T>?, 
                     scheduler: Scheduler? = AndroidSchedulers.mainThread(), 
                     onNext: Consumer<T>?,
                     onError: Consumer<in Throwable>? = Functions.ON_ERROR_MISSING,
                     onComplete: Action? = Functions.EMPTY_ACTION, 
                     onSubscribe: Consumer<in Disposable>? = Functions.emptyConsumer()
                    ): Disposable {
        return toObservable(eventType).observeOn(scheduler).subscribe(onNext,onError,onComplete,onSubscribe)
    }
    
    fun <T> registerSticky(eventType: Class<T>, 
                           scheduler: Scheduler? = AndroidSchedulers.mainThread(),
                           onNext: Consumer<T>?,
                           onError: Consumer<in Throwable>? = Functions.ON_ERROR_MISSING): Disposable {
        return toObservableSticky(eventType).observeOn(scheduler).subscribe(onNext,onError)
    }
    
    /**
     * 移除指定eventType的Sticky事件
     */
    fun <T> removeStickyEvent(eventType: Class<T>): T {
        synchronized(mStickyEventMap) { return eventType.cast(mStickyEventMap.remove(eventType)) }
    }

    /**
     * 移除所有的Sticky事件
     */
    fun removeAllStickyEvents() {
        synchronized(mStickyEventMap) { mStickyEventMap.clear() }
    }

    fun unregister(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    private object Holder {
        val BUS = RxBus()
    }

    companion object {
        fun get(): RxBus {
            return Holder.BUS
        }
    }

    //禁用构造方法
    init {
        bus = PublishRelay.create<Any>().toSerialized()
        mStickyEventMap = ConcurrentHashMap()
    }
}