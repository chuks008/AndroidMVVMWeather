package com.example.mvvweather.data.weather.mapping

data class WeatherData(val currentTemp: String = "",
                       val minTemp: String = "",
                       val maxTemp: String = "",
                       val condition: String = "",
                       val conditionIcon: String = "",
                       val dateMillis: String = "")