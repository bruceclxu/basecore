package com.bruce.imagepicker


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

/**
 * MVVM 当中的一个V层 将三者联系起来
 */
class ImagePickerFragmentDialog : DialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_picker, container,false);
        return view
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        
//         val rxPermissions= RxPermissions(activity = this)
//        this.compositeDisposable +=
//            rxPermissions.request(Manifest.permission.CAMERA, 
//                Manifest.permission.READ_PHONE_STATE)
//                    .subscribe { granted: Boolean ->
//                        if (granted) {
//                            Toast.makeText(this,"已经获取权限 相机权限和 读取手机状态权限",Toast.LENGTH_LONG).show()
//                            // All requested permissions are granted
//                        } else {
//                            Toast.makeText(this,"被用户拒绝",Toast.LENGTH_LONG).show()
//                                // At least one permission is denied
//                        }
//                    }  
//        
//        this.compositeDisposable +=
//            bt_getpic.clicks()
//                .compose(rxPermissions.ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
//                .subscribe { granted: Boolean ->
//                    if (granted) {
//                        Toast.makeText(this,"已经获取权限 相机权限和 读取手机状态权限",Toast.LENGTH_LONG).show()
//                        // All requested permissions are granted
//                    } else {
//                        Toast.makeText(this,"被用户拒绝",Toast.LENGTH_LONG).show()
//                        // At least one permission is denied
//                    }
//                }
//        
//        
//        
//    }
//    
//    override fun getContentViewId(): Int = R.layout.fragment_picker
//
//    override fun initView() {
//    }
}
