package com.example.mvvweather.data.weather.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(@SerializedName("weather") val conditions:
                                  List<WeatherCondition>,
                                  @SerializedName("main") val temperatures:
                                  WeatherDetail)