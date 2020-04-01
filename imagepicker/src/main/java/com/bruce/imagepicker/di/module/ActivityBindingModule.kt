package test.com.bruce.di.module

import com.bruce.imagepicker.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [(BaseActivityComponent::class)])
abstract class ActivityBindingModule {
    
    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
    
}
