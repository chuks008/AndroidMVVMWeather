package com.example.mvvweather.data.location;

import androidx.lifecycle.LiveData;

import com.example.mvvweather.data.location.response.LocationData;

public interface CurrentLocationManager {
    fun getLocation(): LiveData<LocationData>
}
