package com.bruce.core.base

import android.os.Bundle
import androidx.annotation.Nullable
import dagger.android.support.DaggerFragment

abstract class BackHandleFragment : DaggerFragment() {
    private var backHandleInterface: BackHandleInterface? = null

    /**
     * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
     * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
     * 如果没有Fragment消息时FragmentActivity自己才会消费该事件
     */
    open fun onBackPressed(): Boolean{
        (activity as BaseActivity<*>).fragmentBack()
        return true
    }
    
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity is BackHandleInterface) {
            backHandleInterface = activity as BackHandleInterface?
        } else {
            throw ClassCastException("Hosting Activity must implement BackHandledInterface")
        }
        backHandleInterface?.onSelectedFragment(this)

    }

//    override fun onStart() {
//        super.onStart()
//        backHandleInterface?.onSelectedFragment(this)
//    }

    override fun onDestroy() {
        backHandleInterface?.onUnSelectedFragment(this)
        super.onDestroy()
    }
}