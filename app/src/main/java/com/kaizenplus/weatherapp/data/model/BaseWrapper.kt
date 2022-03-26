package com.kaizenplus.weatherapp.data.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class BaseWrapper<T>(
    val data: T
)

