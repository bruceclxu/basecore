package yang.cehome.com.mvvmdemo.model.repository

import com.bruce.core.base.BaseViewModel
import com.bruce.core.network.entity.HttpResponse
import com.bruce.core.network.entity.KuaiDiResult
import io.reactivex.Maybe
import yang.cehome.com.mvvmdemo.model.local.dao.PostDao

/**
 * @author bruce
 *	@desc PostRepo
 *
 */
class PostRepo  constructor(private val local: PostDao): BaseViewModel(){
    //首先查看本地数据库是否有数据
//   fun getPostInfo() = local.getPostInfo()
//           .onErrorResumeNext {
//               //本地数据库不存在，会抛出会抛出EmptyResultSetException
//               //转而进行获取网络数据，成功后保存在数据库
//
//               remote.getPostInfo().doOnSuccess { local.inserttPost(it) }
//           }

    fun getPostInfo(): Maybe<HttpResponse<ArrayList<KuaiDiResult>>> = apiService.getPostInfo()

}