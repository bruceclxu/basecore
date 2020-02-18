package test.com.bruce

import com.bruce.core.BaseApplication
import com.bruce.core.di.component.DaggerCoreComponent
import test.com.bruce.di.component.DaggerAppComponent

/**
 * @author bruce
 *	@desc
 *
 */
class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .coreComponent(coreComponent)
                .build()
                .inject(this)
//        DaggerCoreComponent
//                .builder()
//                .build()
//                .inject(this)
    }
}