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

/**
 * MVVM 当中的一个V层 将三者联系起来
 */
class MainActivity : BaseActivity<PostViewModel>() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initListener()
    }
    
    private fun initListener(){
        compositeDisposable += bt_load.clicks().subscribe {
            viewModel.loadpost()
        }
        compositeDisposable += bt_start_permission.clicks().subscribe {
            startActivity(Intent(this,PermissionTestActivity::class.java))
        }     
    }
    
    private fun setBinding(){
        viewModel.postinfo.observe(this, Observer{
            tv_load.text = it
        })
    }
    
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }
    
}
