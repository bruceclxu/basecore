package com.bruce.imagepicker.data

import com.google.gson.annotations.SerializedName

/**
 * _id : 98061
 * _data : /storage/emulated/0/MIUI/ringtone/金光布袋戏 - 初心无悔 [mqms2].mp3
 * _display_name : 金光布袋戏 - 初心无悔 [mqms2].mp3
 * _size : 8522462
 * mime_type : audio/mpeg
 * date_added : 1515629643
 * is_drm : 0
 * date_modified : 1514789096
 * title : HKC371701671-初心无悔
 * title_key :
 * duration : 213032
 * artist_id : 29
 * album_id : 45
 * track : 0
 * is_ringtone : 0
 * is_music : 1
 * is_alarm : 0
 * is_notification : 0
 * is_podcast : 0
 * artist_id:1 : 29
 * artist_key :
 * artist : 金光布袋戏
 * album_id:1 : 45
 * album_key :
 * album : 金光御九界之东皇战影 剧集原声带
 */
data class MusicModel(

        @SerializedName("_id")
        var id: String? = null,

        @SerializedName("_data")
        var path: String? = null,

        @SerializedName("_display_name")
        var displayName: String? = null,

        @SerializedName("_size")
        var size: Long = 0,

        @SerializedName("mime_type")
        var mimeType: String? = null,

        @SerializedName("date_added")
        var dateAdded: Long = 0,

        @SerializedName("is_drm")
        var isDrm: Boolean = false,

        @SerializedName("date_modified")
        var dateModified: Long = 0,
        var title: String? = null,

        @SerializedName("title_key")
        var titleKey: String? = null,
        var duration: Long = 0,

        @SerializedName("artist_id")
        var artistId: Int = 0,

        @SerializedName("album_id")
        var albumId: Int = 0,
        var track: String? = null,

        @SerializedName("is_ringtone")
        var isRingtone: Boolean = false,

        @SerializedName("is_music")
        var isMusic: Boolean = false,

        @SerializedName("is_alarm")
        var isAlarm: Boolean = false,

        @SerializedName("is_notification")
        var isNotification: Boolean = false,

        @SerializedName("is_podcast")
        var isPodcast: Boolean = false,

        @SerializedName("artist_id:1")
        var artistId_1: Int = 0,

        @SerializedName("artist_key")
        var artistKey: String? = null,
        var artist: String? = null,

        @SerializedName("album_id:1")
        var albumId_1: Int = 0,
        var album_key: String? = null,
        var album: String? = null)