package com.bruce.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bruce.core.rxbus.RxBus
import com.bruce.core.rxbus.event.GlobalNetworkException
import io.reactivex.disposables.Disposable

/**
 * @author Aaron
 * @email aaron@magicwindow.cn
 * @date 18/03/2018 22:15
 * @description
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var mBinding: T
    
    private var disposable: Disposable? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val layoutId = getContentViewId()
        if (layoutId > 0) {
            mBinding = DataBindingUtil.setContentView(this, layoutId)
        }
    }
    
    protected abstract fun getContentViewId(): Int

    protected open var checkException: (Int, String) -> Unit = { code, response ->
        when (code) {
            
        }
    }

    override fun onResume() {
        super.onResume()
        disposable = RxBus.get().register(GlobalNetworkException::class.java) {
            checkException(it.code, it.response)
        }
    }

    override fun onPause() {
        RxBus.get().unregister(disposable)
        super.onPause()
    }
}