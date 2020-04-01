package com.bruce.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bruce.core.rxbus.RxBus
import com.bruce.data.rxevent.GlobalNetworkException
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * @author bruce
 */
abstract class BaseFragment<T:BaseViewModel> : BackHandleFragment() {

    //默认一个 Activity 对应一个viewmodel，如果有多个的场景需要考虑的话
    @Inject
    lateinit var viewModel: T 
    
    var compositeDisposable = CompositeDisposable()
    
    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getContentViewId(), container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        super.onViewCreated(view, savedInstanceState)
    }
    protected abstract fun initView(view:View)
    
    protected abstract fun getContentViewId(): Int

    override fun onResume() {
        super.onResume()
        disposable = RxBus.get().register(eventType = GlobalNetworkException::class.java,onNext = Consumer {
//            checkException(it.code, it.response)
        }) 
    }

    override fun onPause() {
        RxBus.get().unregister(disposable)
        super.onPause()
    }

    override fun onDestroy() {
        compositeDisposable.clear() // 防止内存泄露
        super.onDestroy()
    }
    

}