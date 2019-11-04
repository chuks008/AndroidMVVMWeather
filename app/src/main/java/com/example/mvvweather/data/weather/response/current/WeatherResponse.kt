package com.example.mvvweather.data.weather.response.current

import com.example.mvvweather.data.weather.response.detail.WeatherCondition
import com.example.mvvweather.data.weather.response.detail.WeatherDetail
import com.google.gson.annotations.SerializedName

data class WeatherResponse(@SerializedName("dt") val dateTimeMillis: String,
                           @SerializedName("weather") val conditions:
                                  List<WeatherCondition>,
                           @SerializedName("main") val temperatures:
                                  WeatherDetail
)