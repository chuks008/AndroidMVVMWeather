package com.example.mvvweather.data.autocomplete

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceAutocompleteApi {

    @GET("AutoCompleteCity")
    fun queryPlaceSuggestions(@Query("q") cityQuery: String)
            : Call<List<String>>
}