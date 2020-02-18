package com.bruce.core.network.entity

data class HttpResponse<T>(
        var status: String? = null,
        var message: String? = null,
        var data: T? = null
) : com.bruce.data.httpresponse.UnProguard {
//    val isOkStatus: Boolean
//        get() = status == 0
}