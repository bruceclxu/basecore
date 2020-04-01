package com.bruce.imagepicker

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.bruce.core.base.BaseActivity
import com.bruce.core.base.BaseViewModel
import com.bruce.core.ext.click
import com.bruce.core.rxpermissions.RxPermissions
import com.bruce.imagepicker.adapter.ImageSelectorAdapter
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<BaseViewModel>() {

    var test =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val rxPermissions = RxPermissions(activity = this)
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { granted: Boolean ->
                    if (granted) {
                        Toast.makeText(this, "已经获取权限 相机权限和 读取手机状态权限", Toast.LENGTH_LONG).show()
                        // All requested permissions are granted
                        var imageList = MediaUpAlbumHelper.queryImagesAndVideosFromExternal(this)
                        var imageSelectorAdapter = ImageSelectorAdapter()
                        imageSelectorAdapter.data = imageList
                        var layoutManager = GridLayoutManager(this,4)
                        rv.layoutManager = layoutManager
                        rv.adapter = imageSelectorAdapter
                        
                    } else {
                        Toast.makeText(this, "被用户拒绝", Toast.LENGTH_LONG).show()
                        // At least one permission is denied
                    }
                }
        
        
    }

    override fun getContentViewId(): Int = R.layout.activity_main

    override fun initView() {
        compositeDisposable += bt_start.click().subscribe { 
            startFragment(R.id.content,ImagePickerFragment())
        }
    }

}