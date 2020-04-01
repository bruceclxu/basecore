package com.bruce.imagepicker

import android.content.ContextWrapper
import android.provider.ContactsContract
import android.provider.MediaStore
import com.bruce.imagepicker.data.ContactModel
import com.bruce.imagepicker.data.ImageModel
import com.bruce.imagepicker.data.MusicModel
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import kotlin.collections.ArrayList

object MediaUpAlbumHelper {
    private const val TAG = "ContentResolverHelper"
    private var gson: Gson? = null


    fun queryImagesAndVideosFromExternal(contextWrapper: ContextWrapper): List<ImageModel>{
        var images = queryImagesFromExternal(contextWrapper)
        var videos = queryVideosFromExternal(contextWrapper)
        var list = ArrayList<ImageModel>()
        list.addAll(images)
        list.addAll(videos)
        list.sortByDescending { it.dateModified }
        return list
    }
    
    
    /**
     * 从系统相册中获取图片
     *
     * @param contextWrapper
     * @return
     */
    private fun queryImagesFromExternal(contextWrapper: ContextWrapper): List<ImageModel> {
        val imageModelList: MutableList<ImageModel> = ArrayList()
        val cursor = contextWrapper.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            gson = Gson()
            while (cursor.moveToNext()) {
                try {
                    val json = JSONObject()
                    val columnNames = cursor.columnNames
                    for (columnName in columnNames) {
                        val s = cursor.getString(cursor.getColumnIndex(columnName))
                        json.put(columnName, s)
                    }
                    val imageModel = gson!!.fromJson(json.toString(), ImageModel::class.java)
                    imageModel.selectPosition = -1
                    if(File(imageModel.path).exists()){
                        imageModelList.add(imageModel)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            cursor.close()
        }
        return imageModelList
    }

    /**
     * 从系统相册中获取视频
     *
     * @param contextWrapper
     * @return
     */
    private fun queryVideosFromExternal(contextWrapper: ContextWrapper): List<ImageModel> {
        val imageModelList: MutableList<ImageModel> = ArrayList()
        val cursor = contextWrapper.contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            gson = Gson()
            while (cursor.moveToNext()) {
                try {
                    val json = JSONObject()
                    val columnNames = cursor.columnNames
                    for (columnName in columnNames) {
                        val s = cursor.getString(cursor.getColumnIndex(columnName))
                        json.put(columnName, s)
                    }
                    val imageModel = gson!!.fromJson(json.toString(), ImageModel::class.java)
                    imageModel.selectPosition = -1
                    if(File(imageModel.path).exists()){
                        imageModelList.add(imageModel)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            cursor.close()
        }
        return imageModelList
    }

    /**
     * 获取系统中的音乐文件
     *
     * @param contextWrapper
     * @return
     */
    private fun queryMusicsFromExternal(contextWrapper: ContextWrapper): List<MusicModel> {
        val musicModelList: MutableList<MusicModel> = ArrayList()
        val cursor = contextWrapper.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            gson = Gson()
            while (cursor.moveToNext()) {
                try {
                    val json = JSONObject()
                    val columnNames = cursor.columnNames
                    for (columnName in columnNames) {
                        val s = cursor.getString(cursor.getColumnIndex(columnName))
                        json.put(columnName, s)
                    }
                    val musicModel = gson!!.fromJson(json.toString(), MusicModel::class.java)
                    musicModelList.add(musicModel)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            cursor.close()
        }
        return musicModelList
    }

    /**
     * 获取通讯录联系人
     *
     * @param contextWrapper
     * @return
     */
    private fun queryContacts(contextWrapper: ContextWrapper): List<ContactModel> {
        val contactModelList: MutableList<ContactModel> = ArrayList()
        val cursor = contextWrapper.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            gson = Gson()
            while (cursor.moveToNext()) {
                try {
                    val json = JSONObject()
                    val columnNames = cursor.columnNames
                    for (columnName in columnNames) {
                        val s = cursor.getString(cursor.getColumnIndex(columnName))
                        json.put(columnName, s)
                    }
                    val contactModel = gson!!.fromJson(json.toString(), ContactModel::class.java)
                    contactModelList.add(contactModel)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            cursor.close()
        }
        return contactModelList
    }
}
