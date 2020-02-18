package test.com.bruce.apprepository

import com.bruce.core.base.MaybeUseCase
import com.bruce.core.network.APIService
import io.reactivex.Maybe
import javax.inject.Inject

class TestKuaiDi @Inject constructor():MaybeUseCase<Any,ArrayList<com.bruce.data.httpresponse.KuaiDiResult>>(){
    
    @Inject
    lateinit var service: APIService
    
    override fun buildUseCaseObservable(params: Any?): Maybe<ArrayList<com.bruce.data.httpresponse.KuaiDiResult>>? {
        return service.getPostInfo().map { it.data }
    }
}