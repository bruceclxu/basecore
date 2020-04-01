package com.bruce.core.ext


import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import java.util.concurrent.TimeUnit


fun <T : View> T.click(): io.reactivex.Observable<kotlin.Unit>{
    return clicks().throttleFirst(1000, TimeUnit.MILLISECONDS)
}

