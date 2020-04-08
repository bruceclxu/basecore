package com.bruce.poster

import android.Manifest
import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bruce.core.ext.click
import com.bruce.core.rxpermissions.RxPermissions
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var mGMImageMergeUtils = MGMImageMergeUtils(this)
//        mGMImageMergeUtils
//                .setMerGegeListener(MGMImageMergeUtils.MergeListener(){}).downLoadPics("","","","")
        setContentView(R.layout.activity_main)
        val rxPermissions = RxPermissions(activity = this)

        
        tv.click().subscribe {
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe { granted: Boolean ->
                        if (granted) {
                            MGMImageMergeUtils(this)
                                    .setShareText("dfasdfa")
                                    .setMerGegeListener(object : MGMImageMergeUtils.MergeListener {
                                        override fun getPicSuccess(bitmap: Bitmap?) {
//                        isSharePic = true
                                            Single.create { e: SingleEmitter<Any?> -> e.onSuccess("") }
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .doOnSuccess { s: Any? ->
                                                        iv.setImageBitmap(bitmap)

                                                    }
                                                    .subscribe()
                                        }

                                        override fun getPicErr(errMsg: String?) {
                                            Single.create { e: SingleEmitter<Any?> -> e.onSuccess("") }
                                                    .subscribeOn(Schedulers.io())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .doOnSuccess { s: Any? ->

                                                    }
                                                    .subscribe()
                                        }
                                    }).downLoadPics("",
                                            "",
                                            "")
                        } else {
                            Toast.makeText(this, "被用户拒绝", Toast.LENGTH_LONG).show()
                            // At least one permission is denied
                        }
                    }

        }

    }
}
