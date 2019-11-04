package com.example.mvvweather.data.weather.response.detail

import com.google.gson.annotations.SerializedName

data class WeatherCondition(@SerializedName("description") val condition: String,
                            @SerializedName("icon") val conditionIcon: String)