package com.example.mvvweather.data.autocomplete

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvweather.BuildConfig
import com.example.mvvweather.Constants
import com.example.mvvweather.data.autocomplete.response.AutoCompleteResponse
import com.example.mvvweather.data.autocomplete.response.PlaceData
import com.example.mvvweather.data.location.response.LocationData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class CityFinderRepositoryImpl: CityFindRepository {

    private val TAG = CityFinderRepositoryImpl::class.java.simpleName

    // move to di
    private var placesApi: PlaceAutocompleteApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.CITY_QUERY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        placesApi = retrofit.create(PlaceAutocompleteApi::class.java)
    }

    override fun getCitySuggestions(cityQuery: String): LiveData<List<PlaceData>> {

        val citySuggestionData = MutableLiveData<List<PlaceData>>()


       placesApi.queryPlaceSuggestions(cityQuery).enqueue(object: Callback<AutoCompleteResponse> {
            override fun onFailure(call: Call<AutoCompleteResponse>, t: Throwable) {
                Log.e(TAG,
                    "Error getting city suggestions with network failure: ${t.localizedMessage}")
                citySuggestionData.value = Collections.emptyList()
            }

            override fun onResponse(call: Call<AutoCompleteResponse>,
                                    response: Response<AutoCompleteResponse>) {
                if(!response.isSuccessful) {
                    Log.e(TAG, "Error getting city suggestions with response error")
                    citySuggestionData.value = Collections.emptyList()
                } else {

                    Log.i(TAG, "API Key: ${BuildConfig.PLACES_API_KEY}")
                    Log.i(TAG, "Params:  ${call.request().body()}")
                    Log.e(TAG, "Success getting city suggestions: ${response.body()}")
                    val resultList = ArrayList<PlaceData>()
                    response.body()?.predictions?.forEach {prediction ->
                        resultList.add(PlaceData(prediction.description, prediction.placeId))
                    }

                    citySuggestionData.value = resultList
                }
            }
        })

        return citySuggestionData
    }

    override fun getCityData(city: String): LiveData<LocationData> {
        val cityData = MutableLiveData<LocationData>()
        return cityData
    }
}