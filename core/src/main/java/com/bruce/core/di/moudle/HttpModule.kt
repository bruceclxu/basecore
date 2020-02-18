package com.bruce.core.di.moudle


import ccom.bruce.core.network.interceptor.HeaderInterceptor
import com.bruce.core.network.interceptor.NetworkExceptionInterceptor
import com.bruce.core.BuildConfig
import com.bruce.core.network.APIService
import com.bruce.core.network.adapter.GsonStringNullAdapter
import com.bruce.core.network.interceptor.LoggingInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class HttpModule {

    @Singleton
    @Provides
    internal fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Singleton
    @Provides
    internal fun provideOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Singleton
    @Provides
    internal fun provideService(retrofit: Retrofit): APIService = retrofit.create<APIService>(APIService::class.java)

    @Singleton
    @Provides
    internal fun provideRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit = builder
            .baseUrl(APIService.API_BASE_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .registerTypeAdapter(String::class.java, GsonStringNullAdapter()) //添加 gson null值String的处理，如果是null，将值改为""
                    .create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client)
            .build()

    @Singleton
    @Provides
    internal fun provideClient(builder: OkHttpClient.Builder): OkHttpClient {
        val client = builder
                .writeTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
                .readTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
                .connectTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(HeaderInterceptor())    //设置拦截器
                .addInterceptor(NetworkExceptionInterceptor())
                .addInterceptor(loggingInterceptor)
                .build()

//        return CertifyUtils.getSSLClientIgnoreExpire(client)
        return client
    }

    private val loggingInterceptor = LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .hideVerticalLine()
            .request()
            .requestTag("Request")
            .response()
            .responseTag("Response")
            .build()
}
