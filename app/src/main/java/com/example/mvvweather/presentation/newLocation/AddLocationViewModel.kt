package com.example.mvvweather.presentation.newLocation

import androidx.lifecycle.*
import com.example.mvvweather.data.location.response.LocationData
import com.example.mvvweather.data.places.CityFinderRepository
import com.example.mvvweather.data.storage.WeatherStoreEntry
import com.example.mvvweather.data.storage.WeatherStoreRepository
import com.example.mvvweather.data.weather.WeatherRepository
import com.example.mvvweather.data.weather.mapping.WeatherData
import com.example.mvvweather.presentation.adapter.weather_info.WeatherDataView
import com.example.mvvweather.presentation.weather.WeatherQuery
import javax.inject.Inject
import javax.inject.Singleton

class AddLocationViewModel @Inject constructor(cityFinderRepo: CityFinderRepository,
                                               weatherRepo: WeatherRepository,
                                               weatherStoreRepository: WeatherStoreRepository): ViewModel() {

    private val _cityQueryString = MutableLiveData<String>() // for the city queries
    private val _cityDetailQueryString = MutableLiveData<String>()
    private val _addedCityLiveData = MutableLiveData<Boolean>()
    private var currentCity = ""
    private var currentCountry = ""

    /**
     * Get the current city data under query
     */
    private val cityDetail: LiveData<LocationData> = Transformations.switchMap(_cityDetailQueryString) {queryString ->
        cityFinderRepo.getCityData(queryString)
    }

    /**
     * Get suggestions for possible locations based on the query string. This will show the results
     * In the UI
     */
    val citySuggestions: LiveData<List<String>> = Transformations.switchMap(_cityQueryString) { queryString ->
        cityFinderRepo.getCitySuggestions(queryString)

    } // for the results

    /**
     * Get the five day forecast for the a particular location
     */
    private val cityWeatherData: LiveData<List<WeatherData>> = Transformations.switchMap(cityDetail) { locationData ->
        weatherRepo.get5DayForecast(WeatherQuery(locationData.city))
    }

    /**
     * Final result of a particular location which includes the following: Location data
     * and five-day forecast
     * Change the value of the data store live data to save the location to local storage
     */
    val isCurrentWeatherEntrySaved: LiveData<Boolean> = Transformations.switchMap(cityWeatherData) {forecastList ->

        // take first forecast value, and show to user
        // send the remainder to a db for caching

        val weatherEntry = WeatherStoreEntry(currentCity, forecastList[0], forecastList)

        _addedCityLiveData.value = weatherStoreRepository.addWeatherEntry(weatherEntry)
        _addedCityLiveData
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