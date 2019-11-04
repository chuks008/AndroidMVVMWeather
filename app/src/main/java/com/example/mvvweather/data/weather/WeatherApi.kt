package com.example.mvvweather.data.weather

import com.example.mvvweather.BuildConfig
import com.example.mvvweather.data.weather.response.current.WeatherResponse
import com.example.mvvweather.data.weather.response.forecast.FiveDayForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    fun getCurrentWeather(@Query("lat") latitude: Float,
                          @Query("lon") longitude: Float,
                          @Query("q") city: String,
                          @Query("appid") apiKey: String = BuildConfig.OpenWeatherKey,
                          @Query("units") tempUnit: String = "metric"):
            Call<WeatherResponse>

    @GET("data/2.5/forecast")
    fun getFiveDayForecast(@Query("q") city: String,
                           @Query("appid") apiKey: String = BuildConfig.OpenWeatherKey,
                           @Query("units") tempUnit: String = "metric"):
                Call<FiveDayForecastResponse>
}