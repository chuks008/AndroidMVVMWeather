package com.example.mvvweather.data.location.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(@SerializedName("country_name")
                            val country: String,
                            @SerializedName("country")
                            val coutryCode: String,
                            @SerializedName("city")
                            val city: String,
                            @SerializedName("latitude")
                            val lat: Float,
                            @SerializedName("longitude")
                            val long: Float)