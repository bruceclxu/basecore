package test.com.bruce.viewmodel

import androidx.lifecycle.MutableLiveData
import com.bruce.core.base.BaseViewModel
import com.bruce.core.base.CoreResourceMaybeObserver
import test.com.bruce.apprepository.TestKuaiDi
import javax.inject.Inject

/**
 * @author bruce
 *	@desc  PostViewModel
 */
class PostViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var testKuaiDi: TestKuaiDi

    /******data******/
    @Inject
    lateinit var  postinfo : MutableLiveData<String>

    /******binding******/
    fun loadpost() {
        testKuaiDi.execute(object :CoreResourceMaybeObserver<ArrayList<com.bruce.data.httpresponse.KuaiDiResult>>(){
            override fun onSuccess(t: ArrayList<com.bruce.data.httpresponse.KuaiDiResult>) {
                postinfo.postValue(t[0].context!!)
            }
        })
    }
}