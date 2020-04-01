package com.bruce.imagepicker.data

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * _id : 173463
 * _data : /storage/emulated/0/DCIM/Camera/IMG_20180409_102543_BURST1.jpg
 * _size : 5440606
 * _display_name : IMG_20180409_102543_BURST1.jpg
 * mime_type : image/jpeg
 * title : IMG_20180409_102543_BURST1
 * date_added : 1523240743
 * date_modified : 1523240743
 * latitude : 24.5044
 * longitude : 118.142
 * datetaken : 1523240743224
 * orientation : 90
 * bucket_id : -1739773001
 * bucket_display_name : Camera
 * width : 4032
 * height : 3016
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ImageModel(
        @SerializedName("_id")
        var id: String,
        @SerializedName("_data")
        var path: String,
        @SerializedName("_size")
        var size: Long,
        @SerializedName("_display_name")
        val displayName: String,
        @SerializedName("mime_type")
        var mimeType: String,
        var title: String,
        @SerializedName("date_added")
        var dateAdded: Long,
        @SerializedName("date_modified")
        var dateModified: Long,
        var latitude: Double,
        var longitude: Double,
        @SerializedName("datetaken")
        var dateTaken: Long,
        var orientation: Int,
        var bucket_id: Long,
        var bucket_display_name: String,
        var width: Int,
        var height: Int,
        var duration: Int,
        var isSelected: Boolean = false,
        var selectPosition:Int=-1
)  : Parcelable 