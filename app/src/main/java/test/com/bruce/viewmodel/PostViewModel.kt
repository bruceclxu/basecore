package test.com.bruce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.bruce.core.network.entity.KuaiDiResult
import com.bruce.core.repository.TestKuaiDi
import io.reactivex.observers.ResourceMaybeObserver

/**
 * @author bruce
 *	@desc  PostViewModel
 */
class PostViewModel{
    
     var testKuaiDi = TestKuaiDi()
    
    /******data******/
    val postinfo = MutableLiveData<String>()

    /******binding******/
    fun loadpost() {

        testKuaiDi.execute(object : ResourceMaybeObserver<ArrayList<KuaiDiResult>>() {
            override fun onSuccess(it: ArrayList<KuaiDiResult>) {
                postinfo.postValue(it[0].context!!)
            }

            override fun onError(e: Throwable) {
                e?.printStackTrace()
                postinfo.postValue(e?.message ?: "error")
            }

            override fun onComplete() {
            }

        })
        
    }
}