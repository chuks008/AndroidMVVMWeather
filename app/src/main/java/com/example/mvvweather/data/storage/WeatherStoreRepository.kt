package com.example.mvvweather.data.storage

interface WeatherStoreRepository {
    fun getWeatherEntries(): List<WeatherStoreEntry>
    fun addWeatherEntry(weatherStoreEntry: WeatherStoreEntry): Boolean
    fun removeWeatherEntry(cityName: String, lat: Float, lon: Float): Boolean
    fun containsEntries(): Boolean
}