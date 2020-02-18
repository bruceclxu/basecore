package com.bruce.core.di.moudle

import android.app.Application
import com.bruce.core.BaseApplication
import com.bruce.core.base.MaybeUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @Description 全局依赖注入
 * @Author bruce
 * @Version
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(): Application = BaseApplication.instance
    
}