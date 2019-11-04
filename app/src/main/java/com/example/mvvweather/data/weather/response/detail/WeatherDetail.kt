package com.example.mvvweather.data.weather.response.detail

import com.google.gson.annotations.SerializedName

data class WeatherDetail(@SerializedName("temp") val currentTemp: Float,
                         @SerializedName("temp_min") val minTemp: Float,
                         @SerializedName("temp_max") val maxTemp: Float)