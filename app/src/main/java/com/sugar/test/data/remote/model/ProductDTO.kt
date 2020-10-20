package com.sugar.test.data.remote.model
import com.google.gson.annotations.SerializedName

data class ProductDTO (

    @field:SerializedName("title")
    var title : String? = null,

    @field:SerializedName("body_html")
    var body_html : String? = null,

    @field:SerializedName("product_type")
    var product_type : String? = null,

    @field:SerializedName("handle")
    var handle : String? = null,

    @field:SerializedName("variants")
    var variants : List<VariantsDTO>? = null,

    @field:SerializedName("images")
    var images : List<ImagesDataDTO>? = null
)