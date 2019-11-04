package com.example.mvvweather.data.weather.response.forecast

import com.example.mvvweather.data.weather.response.current.WeatherResponse
import com.google.gson.annotations.SerializedName

data class FiveDayForecastResponse(@SerializedName("cod") val errorCode: String,
                                   @SerializedName("list")
                                   val weeklyForecast: List<WeatherResponse>)