//package test.com.bruce.service
//
//import com.bruce.core.network.APIService
//import com.bruce.core.network.entity.HttpResponse
//import com.bruce.data.httpresponse.KuaiDiResult
//import io.reactivex.Maybe
//import retrofit2.http.GET
//
//interface AppAPIService :APIService{
//    //获取快递
//    @GET("/query?type=yuantong&postid=112323")
//    fun getPostInfo(): Maybe<HttpResponse<ArrayList<KuaiDiResult>>>
//
//}
//
////fun APIService.getPostInfo(){
////    @GET("/query?type=yuantong&postid=112323")
////    fun getPostInfo(): Maybe<HttpResponse<ArrayList<KuaiDiResult>>>  
////}