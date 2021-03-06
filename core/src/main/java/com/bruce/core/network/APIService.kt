package com.bruce.core.network

import androidx.lifecycle.LiveData
import com.bruce.core.network.entity.HttpResponse
import com.bruce.data.httpresponse.KuaiDiResult
import io.reactivex.Maybe
import retrofit2.http.GET

interface APIService {
    companion object {
        const val API_BASE_SERVER_URL: String = "http://www.kuaidi100.com"
    }
    //获取快递
    @GET("/query?type=yuantong&postid=112323")
    fun getPostInfo(): Maybe<HttpResponse<ArrayList<KuaiDiResult>>>

    @GET("/query?type=yuantong&postid=112323")
    fun< T : Any> getPostInfo2(): Maybe<HttpResponse<ArrayList<T>>>

    //获取快递
    @GET("/query?type=yuantong&postid=112323")
    fun getPost3(): LiveData<HttpResponse<ArrayList<KuaiDiResult>>>
}