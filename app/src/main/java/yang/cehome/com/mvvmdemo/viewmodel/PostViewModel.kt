package yang.cehome.com.mvvmdemo.viewmodel

import androidx.databinding.ObservableField
import com.bruce.core.network.entity.HttpResponse
import com.bruce.core.network.entity.KuaiDiResult
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import yang.cehome.com.mvvmdemo.model.local.dao.PostEntity
import yang.cehome.com.mvvmdemo.model.repository.PostRepo

/**
 * @author bruce
 *	@desc  PostViewModel
 *
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