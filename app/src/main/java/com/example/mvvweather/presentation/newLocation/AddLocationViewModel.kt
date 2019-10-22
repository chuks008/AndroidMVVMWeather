package com.example.mvvweather.presentation.newLocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvweather.data.autocomplete.CityFinderRepositoryImpl

class AddLocationViewModel: ViewModel() {

    // move to di
    private val cityFinderRepo = CityFinderRepositoryImpl()

    private var cityQueryString = MutableLiveData<String>() // for the city queries

    val citySuggestions: LiveData<List<String>> = Transformations.switchMap(cityQueryString) { queryString ->
        cityFinderRepo.getCitySuggestions(queryString)
    } // for the results

    fun getCityByName(query: String) {
        cityQueryString.value = query
    }
    
}