package com.example.mvvweather.data.places

import androidx.lifecycle.LiveData
import com.example.mvvweather.data.location.response.LocationData

interface CityFinderRepository {

    fun getCitySuggestions(cityQuery: String): LiveData<List<String>>
    fun getCityData(city: String): LiveData<LocationData>
}