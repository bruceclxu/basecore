package com.bruce.imagepicker.di.component

import com.bruce.core.BaseApplication
import com.bruce.core.di.component.CoreComponent
import com.bruce.core.di.scope.AppScope
import com.bruce.imagepicker.di.module.ActivityBindingModule
import com.bruce.imagepicker.di.module.FragmentBindingModule
import com.bruce.imagepicker.di.providemodule.ProviderOtherModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * @Description
 * @Author bruce.xu
 * @Date 16/04/2018 3:20 PM
 * @Version
 */

@AppScope
@Component(
        dependencies = [CoreComponent::class],
        modules = [
            ActivityBindingModule::class,
            FragmentBindingModule::class,
            AndroidSupportInjectionModule::class,
            ProviderOtherModule::class])
interface AppComponent {
    fun inject(app: BaseApplication)
}