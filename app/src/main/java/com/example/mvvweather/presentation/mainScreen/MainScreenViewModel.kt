package com.example.mvvweather.presentation.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvweather.data.location.CurrentLocationManager
import com.example.mvvweather.data.location.response.LocationData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainScreenViewModel @Inject constructor(locationMgr: CurrentLocationManager) : ViewModel() {

    private var _currentLocationData: LiveData<LocationData>

    init {
        _currentLocationData = locationMgr.getLocation()
    }

    fun fetchLocation(): LiveData<LocationData> {
        return _currentLocationData
    }
}