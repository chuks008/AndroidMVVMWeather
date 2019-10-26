package com.example.mvvweather.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvweather.data.weather.WeatherRepository
import com.example.mvvweather.data.weather.response.WeatherData
import javax.inject.Inject

class WeatherInfoViewModel @Inject constructor(private val weatherRepo: WeatherRepository)
    : ViewModel() {

    private var _weatherQuery = MutableLiveData<WeatherQuery>()

    val currentWeather: LiveData<WeatherData> = Transformations.switchMap(_weatherQuery) {query ->
        weatherRepo.getCurrentWeather(query)
    }

    fun setCurrentWeather(city: String, latitude: Float = 0f, longitude: Float = 0f) {
        _weatherQuery.value = WeatherQuery(city, latitude, longitude)
    }
}