package com.example.mvvweather.data.autocomplete

import com.example.mvvweather.BuildConfig
import com.example.mvvweather.data.autocomplete.response.AutoCompleteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceAutocompleteApi {

    @GET("maps/api/place/autocomplete/json")
    fun queryPlaceSuggestions(@Query("input") cityQuery: String,
                    @Query("types") localeTypes: String = "regions",
                    @Query("key") apiKey: String = BuildConfig.PLACES_API_KEY)
            : Call<AutoCompleteResponse>
}