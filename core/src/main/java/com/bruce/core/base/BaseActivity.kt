package com.bruce.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bruce.core.rxbus.RxBus
import com.bruce.core.rxbus.event.GlobalNetworkException
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * @author bruce
 */
abstract class BaseActivity : AppCompatActivity() {

    var compositeDisposable = CompositeDisposable()
    
    private var disposable: Disposable? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val layoutId = getContentViewId()
        if (layoutId > 0) {
            setContentView(layoutId)
        }
    }
    
    protected abstract fun getContentViewId(): Int

    protected open var checkException: (String, String) -> Unit = { code, _ ->
        when (code) {
//            response.length
        }
    }

    override fun onResume() {
        super.onResume()
        disposable = RxBus.get().register(eventType = GlobalNetworkException::class.java,onNext = Consumer {
            checkException(it.code, it.response)
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