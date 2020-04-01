package com.bruce.imagepicker

import android.view.View
import com.bruce.core.base.BaseFragment
import com.bruce.core.base.BaseViewModel
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.fragment_picker.view.*

/**
 * MVVM 当中的一个V层 将三者联系起来
 */
class ImagePickerFragment : BaseFragment<BaseViewModel>(){

    override fun initView(view:View) {
        (activity as MainActivity).test++
        view.bt_getpic.text = (activity as MainActivity).test.toString()
        view.bt_getpic.clicks().subscribe {

        }    
    }

    override fun getContentViewId() = R.layout.fragment_picker

}
