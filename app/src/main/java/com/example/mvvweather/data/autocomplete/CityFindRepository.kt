package com.example.mvvweather.data.autocomplete

import androidx.lifecycle.LiveData
import com.example.mvvweather.data.autocomplete.response.PlaceData
import com.example.mvvweather.data.location.response.LocationData

interface CityFindRepository {

    fun getCitySuggestions(cityQuery: String): LiveData<List<PlaceData>>
    fun getCityData(city: String): LiveData<LocationData>
}