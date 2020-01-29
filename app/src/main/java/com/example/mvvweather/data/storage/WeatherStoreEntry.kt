package com.example.mvvweather.data.storage

import com.example.mvvweather.data.weather.mapping.WeatherData
import com.google.gson.annotations.SerializedName

data class WeatherStoreEntry(@SerializedName("city_name") val cityName: String,
                             @SerializedName("current_weather") val currentWeather: WeatherData,
                             @SerializedName("five_day_forecast") val fiveDayForecast: List<WeatherData>)