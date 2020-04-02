package com.bruce.imagepicker.ui

import android.Manifest
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bruce.core.base.BaseFragment
import com.bruce.core.ext.click
import com.bruce.core.rxpermissions.RxPermissions
import com.bruce.imagepicker.R
import com.bruce.imagepicker.adapter.ImageSelectorAdapter
import com.bruce.imagepicker.data.ImageModel
import com.bruce.imagepicker.viewmodel.ImageSelectorViewModel
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_picker.*
import kotlinx.android.synthetic.main.fragment_picker.view.*

/**
 * MVVM 当中的一个V层 将三者联系起来
 */
class ImagePickerFragment : BaseFragment<ImageSelectorViewModel>(){
    var imageSelectorAdapter = ImageSelectorAdapter()

    override fun initView(view:View) {
        var layoutManager = GridLayoutManager(context,4)
        view.rv.layoutManager = layoutManager
        view.rv.adapter = imageSelectorAdapter

        setBinding()
        initListener()
        val rxPermissions = RxPermissions(fragment = this)
        compositeDisposable+=rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { granted: Boolean ->
                    if (granted) {
                        context?.let { viewModel.loadData(context = it) }
                    } else {
                        Toast.makeText(context, "被用户拒绝", Toast.LENGTH_LONG).show()
                        // At least one permission is denied
                    }
                }
        
    }

    
    override fun getContentViewId() = R.layout.fragment_picker

    private fun setBinding()=
            viewModel.postinfo.observe(this, Observer{
                imageSelectorAdapter.data = it
                imageSelectorAdapter.notifyDataSetChanged()

            })
    
    private fun initListener(){
        compositeDisposable += bt_complete.click().subscribe {
            for (imageModel in imageSelectorAdapter.selectList!!){
                Log.e("imageModel",imageModel.path)
            }
//            imageSelectorAdapter.selectList
            onBackPressed()
        }
    }
}
