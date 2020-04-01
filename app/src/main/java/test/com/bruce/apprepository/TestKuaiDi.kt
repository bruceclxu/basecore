package test.com.bruce.apprepository

import com.bruce.core.base.MaybeUseCase
import com.bruce.core.network.APIService
import com.bruce.data.httpresponse.KuaiDiResult
import io.reactivex.Maybe
import javax.inject.Inject

class TestKuaiDi @Inject constructor():MaybeUseCase<Any,ArrayList<KuaiDiResult>>(){
    
    @Inject
    lateinit var service: APIService
    
    override fun buildUseCaseObservable(params: Any?): Maybe<ArrayList<KuaiDiResult>>? = 
//            service.getPostInfo2<KuaiDiResult>().map { it.data }
            service.getPostInfo().map { it.data }
    
}