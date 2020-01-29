package com.example.mvvweather.data.weather.mapping

import com.google.gson.annotations.SerializedName

data class WeatherData(@SerializedName("current") val currentTemp: String = "",
                       @SerializedName("min") val minTemp: String = "",
                       @SerializedName("max") val maxTemp: String = "",
                       @SerializedName("condition") val condition: String = "",
                       @SerializedName("icon") val conditionIcon: String = "",
                       @SerializedName("date") val dateMillis: String = "")