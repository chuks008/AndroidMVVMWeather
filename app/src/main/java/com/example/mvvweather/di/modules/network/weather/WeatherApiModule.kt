package com.example.mvvweather.di.modules.network.weather

import com.example.mvvweather.Constants
import com.example.mvvweather.data.weather.WeatherApi
import com.example.mvvweather.data.weather.WeatherRepository
import com.example.mvvweather.data.weather.WeatherRepositoryImpl
import com.example.mvvweather.di.modules.network.NetworkModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class WeatherApiModule {

    @Singleton
    @Provides
    @Named(Constants.OPEN_WEATHER_NAME)
    fun provideWeatherApiRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.OPEN_WEATHER_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(@Named(Constants.OPEN_WEATHER_NAME) retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository {
        return weatherRepository
    }
}