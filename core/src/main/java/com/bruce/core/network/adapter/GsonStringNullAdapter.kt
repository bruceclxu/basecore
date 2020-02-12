package com.bruce.core.network.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

/***
 *@useage   gson 解析，解决服务器返回 null 字段处理为""
 * @Author bruce
 **/
class GsonStringNullAdapter : TypeAdapter<String>(){
        override fun write(out: JsonWriter?, value: String?) {
            if(value == null ){
                out?.nullValue()
                return
            }
            out?.value(value)
        }

        override fun read(inreader: JsonReader?): String {
            try {
                if(inreader?.peek() == JsonToken.NULL){
                    inreader.nextNull()
                    return ""
                }
                return inreader!!.nextString()
            }catch (e:Exception){
                e.printStackTrace()
            }
            return ""
        }

}