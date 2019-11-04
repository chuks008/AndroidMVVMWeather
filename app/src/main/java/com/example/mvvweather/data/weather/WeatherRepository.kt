package com.example.mvvweather.data.weather

import androidx.lifecycle.LiveData
import com.example.mvvweather.data.weather.mapping.WeatherData
import com.example.mvvweather.presentation.weather.WeatherQuery

interface WeatherRepository {

    fun getCurrentWeather(weatherQuery: WeatherQuery): LiveData<WeatherData>
    fun get5DayForecast(weatherQuery: WeatherQuery): LiveData<List<WeatherData>>
}