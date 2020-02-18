package test.com.bruce.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bruce.core.base.BaseActivity
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_main.*
import test.com.bruce.R
import test.com.bruce.viewmodel.PostViewModel
import javax.inject.Inject

/**
 * MVVM 当中的一个V层 将三者联系起来
 */
class MainActivity : BaseActivity() {
    
    @Inject
    lateinit var mViewMode2: PostViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
    }
    
    private fun setBinding(){
        compositeDisposable += bt_load.clicks().subscribe {
            mViewMode2.loadpost()
        }
        compositeDisposable += bt_start_permission.clicks().subscribe {
            startActivity(Intent(this,PermissionTestActivity::class.java))
        }

        mViewMode2.postinfo.observe(this, Observer{
            tv_load.text = it
        })
        
    }
    
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }
    
}
