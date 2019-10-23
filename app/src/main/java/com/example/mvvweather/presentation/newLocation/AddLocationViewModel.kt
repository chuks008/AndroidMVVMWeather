package com.example.mvvweather.presentation.newLocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvvweather.data.location.response.LocationData
import com.example.mvvweather.data.places.CityFinderRepositoryImpl

class AddLocationViewModel: ViewModel() {

    // move to di
    private val cityFinderRepo = CityFinderRepositoryImpl()

    private var _cityQueryString = MutableLiveData<String>() // for the city queries

    private var _cityDetailQueryString = MutableLiveData<String>()

    val citySuggestions: LiveData<List<String>> = Transformations.switchMap(_cityQueryString) { queryString ->
        cityFinderRepo.getCitySuggestions(queryString)
    } // for the results

    val cityDetail: LiveData<LocationData> = Transformations.switchMap(_cityDetailQueryString) {queryString ->
        cityFinderRepo.getCityData(queryString)
    }

    fun getCityByName(query: String) {
        _cityQueryString.value = query
    }

    fun getCityDetail(query: String) {
        _cityDetailQueryString.value = query
    }
    
}