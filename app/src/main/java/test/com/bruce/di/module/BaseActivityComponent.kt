package test.com.bruce.di.module

import com.bruce.core.base.BaseActivity
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

/**
 * 在 [ActivityBindingModule] 中被使用
 * 不要在每个Activity 中建立一个ActivitySubComponent，麻烦而且重复的无聊代码
 **/
@Subcomponent(modules = [(AndroidInjectionModule::class)])
interface BaseActivityComponent : AndroidInjector<BaseActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BaseActivity>()

}
