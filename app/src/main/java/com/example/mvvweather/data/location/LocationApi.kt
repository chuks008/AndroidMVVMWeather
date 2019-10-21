package com.example.mvvweather.data.location

import com.example.mvvweather.data.location.response.LocationResponse
import retrofit2.Call
import retrofit2.http.GET

interface LocationApi {

    @GET("json/")
    fun getCurrentLocation(): Call<LocationResponse>
}