package com.example.mvvweather.presentation

import androidx.lifecycle.ViewModel
import com.example.mvvweather.data.storage.WeatherStoreRepository
import javax.inject.Inject

class StartViewModel @Inject
constructor(private val weatherStoreRepository: WeatherStoreRepository): ViewModel() {

    fun containsWeatherEntries(): Boolean {
        return weatherStoreRepository.containsEntries()
    }
}