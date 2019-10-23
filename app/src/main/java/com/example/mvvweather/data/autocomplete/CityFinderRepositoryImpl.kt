package com.example.mvvweather.data.autocomplete

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvweather.Constants
import com.example.mvvweather.data.location.response.LocationData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

class CityFinderRepositoryImpl: CityFindRepository {

    private val TAG = CityFinderRepositoryImpl::class.java.simpleName

    // move to di
    private var placesApi: PlaceAutocompleteApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.CITY_QUERY_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        placesApi = retrofit.create(PlaceAutocompleteApi::class.java)
    }

    override fun getCitySuggestions(cityQuery: String): LiveData<List<String>> {

        val citySuggestionData = MutableLiveData<List<String>>()


       placesApi.queryPlaceSuggestions(cityQuery).enqueue(object: Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e(TAG,
                    "Error getting city suggestions with network failure: ${t.localizedMessage}")
                citySuggestionData.value = Collections.emptyList()
            }

            override fun onResponse(call: Call<List<String>>,
                                    response: Response<List<String>>) {
                if(!response.isSuccessful) {
                    Log.e(TAG, "Error getting city suggestions with response error")
                    citySuggestionData.value = Collections.emptyList()
                } else {

                    Log.i(TAG, "Request:  ${call.request().url()}")
                    Log.e(TAG, "Success getting city suggestions: ${response.body()?.size}")

                    citySuggestionData.value = response.body()
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