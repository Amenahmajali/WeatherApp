package com.kaizenplus.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)

data class RequestResponse(
    @Json(name = "type")
    var type: String,

    @Json(name = "query")
    var query: String,

    )
