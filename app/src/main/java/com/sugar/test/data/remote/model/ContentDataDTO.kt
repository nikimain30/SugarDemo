package com.sugar.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class ContentDataDTO (

    @field:SerializedName("mediaUrl")
    var mediaUrl: String? = null,

    @field:SerializedName("product_json")
    var productJson: ProductDTO? = null,

    @field:SerializedName("redirectUrl")
    var redirectUrl: String? = null
)