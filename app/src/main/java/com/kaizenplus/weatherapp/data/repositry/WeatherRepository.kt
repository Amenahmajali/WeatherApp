package com.kaizenplus.weatherapp.data.repositry

import com.kaizenplus.weatherapp.data.datasource.WeatherDataSource
import com.kaizenplus.weatherapp.data.model.WeatherCity
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDataSource: WeatherDataSource,
) {

    suspend fun getWeather(query: String): WeatherCity {
        val currentCondition = weatherDataSource.getWeather(query, KEY).data.currentCondition[0]

        return WeatherCity(query, "${currentCondition.tempC} C", currentCondition.observationTime)

    }

    companion object {
        const val KEY = "d5e964b0eb3949268a373148222303"
    }
}