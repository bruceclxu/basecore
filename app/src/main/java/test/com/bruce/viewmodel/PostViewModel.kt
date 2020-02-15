package test.com.bruce.viewmodel

import androidx.databinding.ObservableField
import com.bruce.core.network.entity.HttpResponse
import com.bruce.core.network.entity.KuaiDiResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import test.com.bruce.model.repository.PostRepo

/**
 * @author bruce
 *	@desc  PostViewModel
 */
class PostViewModel(private val repo: PostRepo) {
    /******data******/
    val postinfo = ObservableField<String>()

    /******binding******/
    fun loadpost() {

        repo.getPostInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response:HttpResponse<ArrayList<KuaiDiResult>>->
                    postinfo.set(response?.data?.let { 
                        it[0]?.context!! 
                    })
                },{t: Throwable? ->
                    t?.printStackTrace()
                    postinfo.set(t?.message ?: "error")
                })
    }
}