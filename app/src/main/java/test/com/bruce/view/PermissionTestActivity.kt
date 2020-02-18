package test.com.bruce.view

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import com.bruce.core.base.BaseActivity
import com.bruce.core.rxpermissions.RxPermissions
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_permission_test.*
import test.com.bruce.R

/**
 * MVVM 当中的一个V层 将三者联系起来
 */
class PermissionTestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        var rxPermissions= RxPermissions(this)
        compositeDisposable.add(
            rxPermissions.request(Manifest.permission.CAMERA, 
                Manifest.permission.READ_PHONE_STATE)
                    .subscribe { granted: Boolean ->
                        if (granted) {
                            Toast.makeText(this,"已经获取权限 相机权限和 读取手机状态权限",Toast.LENGTH_LONG).show()
                            // All requested permissions are granted
                        } else {
                            Toast.makeText(this,"被用户拒绝",Toast.LENGTH_LONG).show()
                                // At least one permission is denied
                        }
                    }                
        )
        compositeDisposable.add(
            bt_request.clicks()
                .compose(rxPermissions.ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .subscribe { granted: Boolean ->
                    if (granted) {
                        Toast.makeText(this,"已经获取权限 相机权限和 读取手机状态权限",Toast.LENGTH_LONG).show()
                        // All requested permissions are granted
                    } else {
                        Toast.makeText(this,"被用户拒绝",Toast.LENGTH_LONG).show()
                        // At least one permission is denied
                    }
                }
        )
        
    }
    
    override fun getContentViewId(): Int {
        return R.layout.activity_permission_test
    }
    
}
