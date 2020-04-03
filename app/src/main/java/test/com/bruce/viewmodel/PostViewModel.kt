package test.com.bruce.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.bruce.core.base.BaseViewModel
import com.bruce.core.base.CoreResourceMaybeObserver
import com.bruce.core.base.DataObserver
import com.bruce.core.network.APIService
import com.bruce.core.network.entity.HttpResponse
import com.bruce.data.httpresponse.KuaiDiResult
import test.com.bruce.MyApplication
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
        testKuaiDi.execute(object :CoreResourceMaybeObserver<ArrayList<KuaiDiResult>>(){
            override fun onSuccess(t: ArrayList<KuaiDiResult>) {
                postinfo.postValue(t[0].context!!)
            }
        })
    }

    @Inject
    lateinit var service: APIService
//    /******data******/ 
//    var  postinfoLive : LiveData<HttpResponse<ArrayList<KuaiDiResult>>>? = null
            
    fun loadpost2(owner: LifecycleOwner){
        service.getPost3().observe( owner, object : DataObserver<ArrayList<KuaiDiResult>>() {
            override fun onResponse(result: ArrayList<KuaiDiResult>) {
//                super.onResponse(result)
                postinfo.postValue(result[0].context)
//                getSampleMutableLiveData().setValue(result)
            }

            override fun onError() {

                //可以根据业务决定setValue参数的类型，
//                getSampleErrorMutableLiveData().setValue("errorMessage")
            }
        })
    }
    


}