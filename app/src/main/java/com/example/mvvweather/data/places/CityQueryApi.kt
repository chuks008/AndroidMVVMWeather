package com.example.mvvweather.data.places

import com.example.mvvweather.data.places.response.CityDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CityQueryApi {

    @GET("AutoCompleteCity")
    fun queryPlaceSuggestions(@Query("q") cityQuery: String)
            : Call<List<String>>

    @GET("GetCityDetails")
    fun queryPlaceDetail(@Query("fqcn") detailQuery: String)
        : Call<CityDetailResponse>
}