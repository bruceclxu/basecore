package test.com.bruce.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import test.com.bruce.di.providemodule.ProviderOtherModule
import test.com.bruce.view.MainActivity
import test.com.bruce.view.PermissionTestActivity

@Module(subcomponents = [(BaseActivityComponent::class)])
abstract class ActivityBindingModule {
    
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ProviderOtherModule::class])
    internal abstract fun permissionTestActivity(): PermissionTestActivity
}
