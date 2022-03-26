package com.kaizenplus.weatherapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "request")
    var request: List<RequestResponse>,

    @Json(name = "current_condition")
    var currentCondition: List<CurrentConditionResponse>

)
