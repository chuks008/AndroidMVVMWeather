package com.example.mvvweather.data.places

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvweather.data.location.response.LocationData
import com.example.mvvweather.data.places.response.CityDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityFinderRepositoryImpl @Inject constructor(private val placesApi: CityQueryApi):
    CityFinderRepository {

    private val TAG = CityFinderRepositoryImpl::class.java.simpleName

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
                    Log.e(TAG, "Success getting city suggestions: ${response.body()?.size}")

                    citySuggestionData.value = response.body()
                }
            }
        })

        return citySuggestionData
    }

    override fun getCityData(city: String): LiveData<LocationData> {
        val cityData = MutableLiveData<LocationData>()

        placesApi.queryPlaceDetail(city).enqueue(object: Callback<CityDetailResponse> {
            override fun onFailure(call: Call<CityDetailResponse>, t: Throwable) {
                 Log.e(TAG, "Error getting place details: ${t.localizedMessage}")
                 cityData.value = LocationData(true)
            }

            override fun onResponse(
                call: Call<CityDetailResponse>,
                response: Response<CityDetailResponse>
            ) {
                if(!response.isSuccessful) {

                } else {
                    Log.e(TAG, "Success getting city details")
                    val cityDetails = response.body()
                    cityData.value = LocationData(false,
                        cityDetails!!.city,
                        cityDetails.countryCode,
                        cityDetails.latitude,
                        cityDetails.longitude)
                }
            }
        })

        return cityData
    }
}