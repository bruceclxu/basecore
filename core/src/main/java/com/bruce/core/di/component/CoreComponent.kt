package com.bruce.core.di.component

import android.app.Application
import com.bruce.core.BaseApplication
import com.bruce.core.di.moudle.AppModule
import com.bruce.core.di.moudle.HttpModule
import com.bruce.core.network.APIService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @Description 将CoreComponent中的APIService,OkHttpClient,SPHelper等provide给AppComponent,再provide给module
 *              1,dependencies方式的依赖关系Component,父Component必须提供实例,子Component会通过DaggerAppComponent.this.coreComponent.spHelper()获取
 *              2,通过SubComponent的继承方式的依赖关系Component,父Component无须提供实例,但必须显式的Builder来创建SubComponent.模块化获取不到SubComponent,采用第一种
 * @Author bruce
 * @Version
 */
@Singleton
@Component(modules = [AppModule::class, HttpModule::class, AndroidSupportInjectionModule::class])
interface CoreComponent : AndroidInjector<BaseApplication> {

    fun apiService(): APIService
    
    /**
     * build() 和 seedInstance() 方法已经在 AndroidInjector.Builder 抽象类中定义了，所以我们的 Builder 类可以通过继承 AndroidInjection.Builder<Application> 来去掉上面代码中 application() 和 build() 这两个方法。
     * 同样的，AndroidInjector 接口中已经有 inject() 方法了。所以我们可以通过继承 AndroidInjector<Application> 接口（接口是可以继承接口的）来删除 inject() 方法。
     */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): CoreComponent?
    }
}