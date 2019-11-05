package com.example.mvvweather.presentation.newLocation

import androidx.lifecycle.*
import com.example.mvvweather.data.location.response.LocationData
import com.example.mvvweather.data.places.CityFinderRepository
import com.example.mvvweather.data.weather.WeatherRepository
import com.example.mvvweather.data.weather.mapping.WeatherData
import com.example.mvvweather.presentation.adapter.weather_info.WeatherDataView
import com.example.mvvweather.presentation.weather.WeatherQuery
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddLocationViewModel @Inject constructor(cityFinderRepo: CityFinderRepository,
                                               weatherRepo: WeatherRepository): ViewModel() {

    private val _cityQueryString = MutableLiveData<String>() // for the city queries
    private val _cityDetailQueryString = MutableLiveData<String>()
    private val _addedCitiesLiveData = MutableLiveData<List<WeatherDataView>>()
    private val _addedCities = ArrayList<WeatherDataView>()
    private var currentCity = ""
    private var currentCountry = ""

    private val cityDetail: LiveData<LocationData> = Transformations.switchMap(_cityDetailQueryString) {queryString ->
        cityFinderRepo.getCityData(queryString)
    }

    val citySuggestions: LiveData<List<String>> = Transformations.switchMap(_cityQueryString) { queryString ->
        cityFinderRepo.getCitySuggestions(queryString)

    } // for the results

    private val cityWeatherData: LiveData<List<WeatherData>> = Transformations.switchMap(cityDetail) { locationData ->
        weatherRepo.get5DayForecast(WeatherQuery(locationData.city))
    }

    val weatherDataList: LiveData<List<WeatherDataView>> = Transformations.switchMap(cityWeatherData) {forecastList ->

        val latestWeatherData = forecastList.first() // take first forecast value, and show to user

        // send the remainder to a db for caching

        _addedCities.add(WeatherDataView(
            latestWeatherData.currentTemp,
            latestWeatherData.minTemp,
            latestWeatherData.maxTemp,
            latestWeatherData.condition,
            latestWeatherData.conditionIcon,
            currentCity,
            currentCountry))

        _addedCitiesLiveData.value = _addedCities
        _addedCitiesLiveData
    }

    fun setCityName(query: String) {
        _cityQueryString.value = query
    }

    fun setCityDetail(query: String) {
        val queryBlock = query.split((", ")) // in format <City>, <City code>, <Country>
        currentCity = queryBlock[0]
        currentCountry = queryBlock[2]
        _cityDetailQueryString.value = query
    }
    
}