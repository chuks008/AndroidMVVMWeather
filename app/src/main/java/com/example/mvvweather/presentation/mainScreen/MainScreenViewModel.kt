package com.example.mvvweather.presentation.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvweather.data.location.LocationManager
import com.example.mvvweather.data.location.LocationManagerImpl
import com.example.mvvweather.data.location.response.LocationData

class MainScreenViewModel: ViewModel() {

    private val locationMgr = LocationManagerImpl()
    private var _currentLocationData: LiveData<LocationData>

    init {
        _currentLocationData = locationMgr.location
    }


    fun fetchLocation(): LiveData<LocationData> {
        return _currentLocationData
    }
}