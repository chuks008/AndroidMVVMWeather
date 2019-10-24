package com.example.mvvweather.data.weather.response

data class WeatherData(val currentTemp: String = "",
                       val minTemp: String = "",
                       val maxTemp: String = "",
                       val condition: String = "",
                       val conditionIcon: String = "")