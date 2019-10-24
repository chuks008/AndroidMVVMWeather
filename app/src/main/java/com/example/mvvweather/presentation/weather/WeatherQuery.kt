package com.example.mvvweather.presentation.weather

data class WeatherQuery(val city: String, val lat: Float = 0f, val lon: Float = 0f)