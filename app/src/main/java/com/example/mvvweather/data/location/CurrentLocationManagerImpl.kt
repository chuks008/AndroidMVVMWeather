package com.example.mvvweather.data.location

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvweather.data.location.response.LocationData
import com.example.mvvweather.data.location.response.LocationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentLocationManagerImpl @Inject constructor(private val netClient: LocationApi):
    CurrentLocationManager {

    private val TAG = CurrentLocationManagerImpl::class.java.simpleName

    override fun getLocation(): LiveData<LocationData> {
        val locationLiveData = MutableLiveData<LocationData>()
        netClient.getCurrentLocation().enqueue(object: Callback<LocationResponse> {
            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {

                Log.e(TAG, "Error Occured ${t.localizedMessage}")
                locationLiveData.value = generateLocationData(true)
            }

            override fun onResponse(
                call: Call<LocationResponse>,
                response: Response<LocationResponse>
            ) {
                if(!response.isSuccessful) {
                    Log.e(TAG, "Error Occured with response unsuccessful")
                    locationLiveData.value = generateLocationData(true)
                } else {
                    val body = response.body()!!

                    if(body.city != "") {
                        locationLiveData.value = generateLocationData(false,
                            body.city,
                            body.coutryCode,
                            body.lat,
                            body.long)
                    }
                }
            }
        })

        return locationLiveData
    }

    private fun generateLocationData(error: Boolean = true,
                                     city: String = "",
                                     countryCode: String = "",
                                     lat: Float = 0f,
                                     long: Float = 0f): LocationData {

        return LocationData(error, city, countryCode, lat, long)

    }
}