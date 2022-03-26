package com.kaizenplus.weatherapp.data.datasource

import com.kaizenplus.weatherapp.data.model.BaseWrapper
import com.kaizenplus.weatherapp.data.model.RequestResponse
import com.kaizenplus.weatherapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDataSource {

    @GET("weather.ashx?tp=1&date=today&format=json&num_of_days=1?")
    suspend fun getWeather(
        @Query("q") q: String,
        @Query("key") key: String,

        ): BaseWrapper<WeatherResponse>


}