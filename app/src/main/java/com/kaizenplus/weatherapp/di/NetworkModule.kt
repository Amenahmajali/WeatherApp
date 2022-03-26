package com.kaizenplus.weatherapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT_MINUTES = 1L

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
            .connectTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)


        return builder.build()
    }

    @Provides
    @Singleton
    fun provideWeatherRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.worldweatheronline.com/premium/v1/")
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

    }

    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        val builder = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
        return builder.build()
    }

}