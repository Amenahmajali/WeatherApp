package com.kaizenplus.weatherapp.di

import com.kaizenplus.weatherapp.data.datasource.WeatherDataSource
import com.kaizenplus.weatherapp.data.repositry.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Provides
    fun provideRequestDataSource(retrofit: Retrofit): WeatherDataSource {
        return retrofit.create(WeatherDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideRequestRepository(weatherDataSource: WeatherDataSource): WeatherRepository {
        return WeatherRepository(weatherDataSource)
    }

}