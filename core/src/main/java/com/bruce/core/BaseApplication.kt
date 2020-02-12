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
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
    companion object {

        lateinit var coreComponent: CoreComponent

        //方式1.通过标准代理实现late init
        var instance: BaseApplication by Delegates.notNull()
            private set

//        init {
//            //设置全局的Header,Footer构建器
//            SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, layout -> PullRefreshHeader(context) }
//            SmartRefreshLayout.setDefaultRefreshFooterCreater { context, layout -> ClassicsFooter(context).setDrawableSize(20f) }
//        }
    }
    
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        coreComponent = DaggerCoreComponent.builder().create(this) as CoreComponent
        return coreComponent    
    }
}