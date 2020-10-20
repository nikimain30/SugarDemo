package com.sugar.test.data.model

import com.google.gson.annotations.SerializedName
import com.sugar.test.data.remote.model.ImagesDataDTO
import com.sugar.test.data.remote.model.VariantsDTO
import java.io.Serializable

class Product: Serializable {
    var title : String? = null
    var body_html : String? = null
    var product_type : String? = null
    var handle : String? = null
    var variants : List<Variants>? = null
    var images : List<Images>? = null
}