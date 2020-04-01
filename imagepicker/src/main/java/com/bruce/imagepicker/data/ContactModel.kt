package com.bruce.imagepicker.data

import com.google.gson.annotations.SerializedName

/**
 * account_type : com.xiaomi
 * data_version : 0
 * name_verified : 0
 * display_name_alt : 叶海
 * sort_key_alt : 叶海
 * starred : 0
 * has_phone_number : 1
 * raw_contact_id : 304
 * contact_account_type : com.xiaomi
 * carrier_presence : 0
 * contact_last_updated_timestamp : 1516769191377
 * phonebook_bucket : 25
 * display_name : 叶海
 * sort_key : 叶海
 * version : 3
 * in_default_directory : 1
 * times_contacted : 0
 * _id : 957
 * account_type_and_data_set : com.xiaomi
 * name_raw_contact_id : 304
 * phonebook_bucket_alt : 25
 * last_time_contacted : 0
 * pinned : 0
 * is_primary : 0
 * contact_id : 339
 * in_visible_group : 1
 * phonebook_label : Y
 * account_name : 412979213
 * display_name_source : 40
 * dirty : 0
 * sourceid : 32302221821509761
 * phonetic_name_style : 0
 * send_to_voicemail : 0
 * lookup : 3430i32302221821509761
 * phonebook_label_alt : Y
 * is_super_primary : 0
 * data4 : 19999999999
 * data2 : 2
 * data1 : 199 9999 9999
 * raw_contact_is_user_profile : 0
 * mimetype : vnd.android.cursor.item/phone_v2
 */
data class ContactModel(

        @SerializedName("account_type")
        var accountType: String? = null,

        @SerializedName("data_version")
        var dataVersion: String? = null,

        @SerializedName("name_verified")
        var nameVerified: String? = null,

        @SerializedName("display_name_alt")
        var displayNameAlt: String? = null,

        @SerializedName("sort_key_alt")
        var sortKeyAlt: String? = null,
        var starred: String? = null,

        @SerializedName("has_phone_number")
        var isHasPhoneNumber: Boolean = false,

        @SerializedName("raw_contact_id")
        var rawContactId: String? = null,

        @SerializedName("contact_account_type")
        var contactAccountType: String? = null,

        @SerializedName("carrier_presence")
        var carrierPresence: String? = null,

        @SerializedName("contact_last_updated_timestamp")
        var contactLastPpdatedTimestamp: Long = 0,

        @SerializedName("phonebook_bucket")
        var phonebookBucket: Int = 0,

        @SerializedName("display_name")
        var displayName: String? = null,

        @SerializedName("sort_key")
        var sortKey: String? = null,
        var version: String? = null,

        @SerializedName("in_default_directory")
        var isInDefaultdirectory: Boolean = false,

        @SerializedName("times_contacted")
        var timesContacted: Int = 0,

        @SerializedName("_id")
        var id: String? = null,

        @SerializedName("account_type_and_data_set")
        var accountTypeAndDataSet: String? = null,

        @SerializedName("name_raw_contact_id")
        var nameRawContactId: String? = null,

        @SerializedName("phonebook_bucket_alt")
        var phonebookBucketAlt: String? = null,

        @SerializedName("last_time_contacted")
        var lastTimeContacted: String? = null,
        var pinned: String? = null,

        @SerializedName("is_primary")
        var isIsPrimary: Boolean = false,

        @SerializedName("contact_id")
        var contactId: String? = null,

        @SerializedName("in_visible_group")
        var isInVisibleGroup: Boolean = false,

        @SerializedName("phonebook_label")
        var phonebookLabel: String? = null,

        @SerializedName("account_name")
        var accountName: String? = null,

        @SerializedName("display_name_source")
        var displayNameSource: String? = null,
        var dirty: String? = null,
        var sourceid: String? = null,

        @SerializedName("phonetic_name_style")
        var phoneticNameStyle: String? = null,

        @SerializedName("send_to_voicemail")
        var sendToVoicemail: String? = null,
        var lookup: String? = null,

        @SerializedName("phonebook_label_alt")
        var phonebookLabelAlt: String? = null,

        @SerializedName("is_super_primary")
        var isIsSuperPrimary: Boolean = false,
        var data4: String? = null,
        var data2: String? = null,
        var data1: String? = null,

        @SerializedName("raw_contact_is_user_profile")
        var rawContactIsUserProfile: String? = null,
        var mimetype: String? = null

)