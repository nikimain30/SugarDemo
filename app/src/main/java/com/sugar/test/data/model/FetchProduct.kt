package com.sugar.test.data.model

import com.google.gson.annotations.SerializedName
import com.sugar.test.data.remote.model.ContentDataDTO
import java.io.Serializable

class FetchProduct: Serializable {
    var id: Int? = null
    var title: String? = null
    var contentData: List<ContentDataDTO>? = null
}