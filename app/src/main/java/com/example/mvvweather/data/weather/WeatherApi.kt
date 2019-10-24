package com.example.mvvweather.data.weather

import com.example.mvvweather.BuildConfig
import com.example.mvvweather.data.weather.response.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    fun getCurrentWeather(@Query("lat") latitude: Float,
                          @Query("lon") longitude: Float,
                          @Query("q") city: String,
                          @Query("appid") apiKey: String = BuildConfig.OpenWeatherKey):
            Call<CurrentWeatherResponse>
}