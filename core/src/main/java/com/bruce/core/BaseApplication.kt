package com.bruce.core

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * @author bruce
 *	@desc
 *
 */
open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}