package com.sugar.test.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ContentData : Serializable {

    var mediaUrl: String? = null
    var productJson: Product? = null
    var redirectUrl: String? = null
}