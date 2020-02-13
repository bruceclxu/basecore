package yang.cehome.com.mvvmdemo.view

import android.os.Bundle
import com.bruce.core.base.BaseActivity
import yang.cehome.com.mvvmdemo.R
import yang.cehome.com.mvvmdemo.databinding.ActivityMainBinding
import yang.cehome.com.mvvmdemo.model.data.Onclick
import yang.cehome.com.mvvmdemo.model.local.AppDatabase
import yang.cehome.com.mvvmdemo.model.repository.PostRepo
import yang.cehome.com.mvvmdemo.viewmodel.OnclikViewModel
import yang.cehome.com.mvvmdemo.viewmodel.PostViewModel

/**
 * MVVM 当中的一个V层 将三者联系起来
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var mViewMode: OnclikViewModel
    private lateinit var mViewMode2: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /////model
        val onclick = Onclick("me", 0)
        ///ViewModel
        mViewMode = OnclikViewModel(onclick)

        val local= AppDatabase.getInstance(applicationContext).PostDao()
        val  repo = PostRepo(local)

        ////ViewModel2
        mViewMode2 = PostViewModel(repo)
        mBinding.vm = mViewMode
        ////binding
        mBinding.post = mViewMode2
    }
    
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }
    
}
