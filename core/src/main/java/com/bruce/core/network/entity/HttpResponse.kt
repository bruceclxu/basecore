package com.bruce.core.network.entity

import com.bruce.data.httpresponse.UnProguard

data class HttpResponse<T>(
        var status: String? = null,
        var message: String? = null,
        var data: T? = null
) : UnProguard {
//    val isOkStatus: Boolean
//        get() = status == 0
}