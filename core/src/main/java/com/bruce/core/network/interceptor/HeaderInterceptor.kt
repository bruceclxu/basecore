package ccom.bruce.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @Author bruce
 */

class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val requestBuilder = request.newBuilder()

        requestBuilder.addHeader("Content-Type", "application/json")
        return chain.proceed(requestBuilder.build())
    }
}