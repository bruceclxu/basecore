package com.bruce.imagepicker.di.module


import com.bruce.core.di.scope.FragmentScope
import com.bruce.imagepicker.ui.ImagePickerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @Description 当fragment中需要使用activity声明的注解时,activity必须提供对应module
 */
@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun imagePickerFragment(): ImagePickerFragment

}
