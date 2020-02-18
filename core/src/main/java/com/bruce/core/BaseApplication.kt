package com.bruce.core

import com.bruce.core.di.component.CoreComponent
import com.bruce.core.di.component.DaggerCoreComponent

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import kotlin.properties.Delegates


/**
 * @author bruce
 *	@desc
 */
open class BaseApplication : DaggerApplication() {
//    override fun onCreate() {
//
//        super.onCreate()
//        DaggerCoreComponent.builder().build().inject(this)
//
//        if (BuildConfig.DEBUG) {
//            Stetho.initializeWithDefaults(this);
//        }
//    }
    companion object {

        lateinit var coreComponent: CoreComponent

        //方式1.通过标准代理实现late init
        var instance: BaseApplication by Delegates.notNull()
            private set

    }
    
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        coreComponent = DaggerCoreComponent.builder().application(this)?.build()!!
        return coreComponent    
    }
}