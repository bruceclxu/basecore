package test.com.bruce.view

import android.content.Intent
import android.os.Bundle
import com.bruce.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import test.com.bruce.databinding.ActivityMainBinding
import test.com.bruce.R
import test.com.bruce.model.data.Onclick
import test.com.bruce.model.local.AppDatabase
import test.com.bruce.model.repository.PostRepo
import test.com.bruce.viewmodel.OnclikViewModel
import test.com.bruce.viewmodel.PostViewModel

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

        bt_start_permission.setOnClickListener { 
            startActivity(Intent(this,PermissionTestActivity::class.java))
        }
    }
    
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }
    
}
