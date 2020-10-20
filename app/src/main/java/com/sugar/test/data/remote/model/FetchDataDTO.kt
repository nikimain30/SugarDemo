package com.sugar.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class FetchDataDTO (

    @field:SerializedName("id")
    var id: Int? = null,

    @field:SerializedName("title")
    var title: String? = null,

    @field:SerializedName("contentData")
    var contentData: List<ContentDataDTO>? = null
)