package com.sugar.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class ImagesDataDTO (

    @field:SerializedName("width")
    var width : Int? = null,

    @field:SerializedName("height")
    var height : Int? = null,

    @field:SerializedName("src")
    var src : String? = null
)