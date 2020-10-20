package com.sugar.test.data.remote.model

import com.google.gson.annotations.SerializedName

data class VariantsDTO (

    @field:SerializedName("title")
    var title : String? = null,

    @field:SerializedName("price")
    var price : Double? = null,

    @field:SerializedName("compare_at_price")
    var compare_at_price : Double? = null
)