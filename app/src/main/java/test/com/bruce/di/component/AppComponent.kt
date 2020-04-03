package test.com.bruce.di.component

//import test.com.bruce.di.module.FragmentBindingModule
import com.bruce.core.BaseApplication
import com.bruce.core.di.component.CoreComponent
import com.bruce.core.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import test.com.bruce.MyApplication
import test.com.bruce.di.module.ActivityBindingModule
import test.com.bruce.di.providemodule.ProviderOtherModule

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
//            FragmentBindingModule::class,
            AndroidSupportInjectionModule::class,
            ProviderOtherModule::class])
interface AppComponent {
    fun inject(app: BaseApplication)
}