package com.bruce.imagepicker.ui

import com.bruce.core.base.BaseActivity
import com.bruce.core.ext.click
import com.bruce.imagepicker.R
import com.bruce.imagepicker.viewmodel.ImageSelectorViewModel
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ImageSelectorViewModel>() {

    override fun getContentViewId(): Int = R.layout.activity_main

    
    override fun initView() {
        compositeDisposable += bt_start.click().subscribe { 
            startFragment(R.id.content, ImagePickerFragment())
        }
    }

}