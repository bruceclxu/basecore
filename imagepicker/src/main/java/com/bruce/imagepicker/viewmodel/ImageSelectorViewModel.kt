package com.bruce.imagepicker.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.bruce.core.base.BaseViewModel
import com.bruce.imagepicker.MediaUpAlbumHelper
import com.bruce.imagepicker.data.ImageModel
import javax.inject.Inject

/**
 * @author bruce
 *	@desc  PostViewModel
 */
class ImageSelectorViewModel @Inject constructor(): BaseViewModel() {
    /******data******/
    var  postinfo : MutableLiveData<List<ImageModel>> = MutableLiveData()

    /******binding******/
    fun loadData(context: Context) {
        var imageList = MediaUpAlbumHelper.queryImagesAndVideosFromExternal(context)
        postinfo.postValue(imageList)
    }
}