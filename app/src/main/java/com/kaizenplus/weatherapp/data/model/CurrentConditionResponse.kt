package com.kaizenplus.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CurrentConditionResponse(
    @Json(name = "observation_time")
    var observationTime: String,

    @Json(name = "temp_C")
    var tempC: String,

    )


