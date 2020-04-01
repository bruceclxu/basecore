package com.bruce.core.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bruce.core.rxbus.RxBus
import com.bruce.data.rxevent.GlobalNetworkException
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * @author bruce
 */
abstract class BaseActivity<T:BaseViewModel> : DaggerAppCompatActivity(),BackHandleInterface {
    private var backHandleFragment: ArrayList<BackHandleFragment> = ArrayList()

//    override val kodein by  closestKodein()
//
////    val ds: DataSource by instance()
//    override val kodeinTrigger = KodeinTrigger()

//    lateinit var manager: FragmentManager
    lateinit var transaction : FragmentTransaction
    
    //默认一个 Activity 对应一个viewmodel，如果有多个的场景需要考虑的话
    @Inject
    lateinit var viewModel: T 
    
    var compositeDisposable = CompositeDisposable()
    
    private var disposable: Disposable? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getContentViewId()
        if (layoutId > 0) {
            setContentView(layoutId)
        }
        
        initView()
    }

    protected abstract fun initView()
    
    protected abstract fun getContentViewId(): Int

    protected open var checkException: (String, String) -> Unit = { code, _ ->
        when (code) {
//            response.length
        }
    }

    override fun onResume() {
        super.onResume()
        disposable = RxBus.get().register(eventType = GlobalNetworkException::class.java,onNext = Consumer {
            checkException(it.code, it.response)
        }) 
    }

    override fun onPause() {
        RxBus.get().unregister(disposable)
        super.onPause()
    }

    override fun onDestroy() {
        compositeDisposable.clear() // 防止内存泄露
        super.onDestroy()
    }
    
    fun startFragment(id:Int, fragment:Fragment){
        transaction = supportFragmentManager.beginTransaction()
        transaction.add(id,fragment,"base").addToBackStack("test").commit()
    }
    
    fun fragmentBack(){
        supportFragmentManager.popBackStack()
    }

    override fun onSelectedFragment(backHandleFragment: BackHandleFragment) {
        this.backHandleFragment.add(backHandleFragment)
    }

    override fun onUnSelectedFragment(backHandleFragment: BackHandleFragment) {
        this.backHandleFragment.remove(backHandleFragment)
    }
    
    override fun onBackPressed() {
        if(backHandleFragment.size == 0 || !backHandleFragment[backHandleFragment.size-1].onBackPressed()){
            if(supportFragmentManager.backStackEntryCount == 0){
                super.onBackPressed()
            }else{
                supportFragmentManager.popBackStack()
            }
        }
    }
}