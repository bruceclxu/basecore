package com.bruce.core.repository

import com.bruce.core.BaseApplication
import com.bruce.core.base.MaybeUseCase
import com.bruce.core.network.entity.KuaiDiResult
import io.reactivex.Maybe

class TestKuaiDi :MaybeUseCase<Any,ArrayList<KuaiDiResult>>(){
    
    var service = BaseApplication.coreComponent.apiService()
    
    override fun buildUseCaseObservable(params: Any?): Maybe<ArrayList<KuaiDiResult>>? {
        return service.getPostInfo().map { it.data }
    }
}