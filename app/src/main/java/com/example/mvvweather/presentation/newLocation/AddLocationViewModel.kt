package com.example.mvvweather.presentation.newLocation

import androidx.lifecycle.*
import com.example.mvvweather.data.location.response.LocationData
import com.example.mvvweather.data.places.CityFinderRepository
import com.example.mvvweather.data.weather.WeatherRepository
import com.example.mvvweather.data.weather.mapping.WeatherData
import com.example.mvvweather.presentation.weather.WeatherQuery
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddLocationViewModel @Inject constructor(cityFinderRepo: CityFinderRepository,
                                               weatherRepo: WeatherRepository): ViewModel() {

    private val _cityQueryString = MutableLiveData<String>() // for the city queries
    private val _cityDetailQueryString = MutableLiveData<String>()

    private val cityDetail: LiveData<LocationData> = Transformations.switchMap(_cityDetailQueryString) {queryString ->
        cityFinderRepo.getCityData(queryString)
    }

    val citySuggestions: LiveData<List<String>> = Transformations.switchMap(_cityQueryString) { queryString ->
        cityFinderRepo.getCitySuggestions(queryString)

    } // for the results

    val cityWeatherData: LiveData<List<WeatherData>> = Transformations.switchMap(cityDetail) { locationData ->
        weatherRepo.get5DayForecast(WeatherQuery(locationData.city))
    }

    fun setCityName(query: String) {
        _cityQueryString.value = query
    }

    fun setCityDetail(query: String) {
        _cityDetailQueryString.value = query
    }
    
}