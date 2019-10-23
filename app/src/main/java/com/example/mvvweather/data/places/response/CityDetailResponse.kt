package com.example.mvvweather.data.places.response

import com.google.gson.annotations.SerializedName

data class CityDetailResponse(@SerializedName("geobytescity") val city: String,
                              @SerializedName("geobytescode") val countryCode: String,
                              @SerializedName("geobyteslatitude") val latitude: Float,
                              @SerializedName("geobyteslongitude") val longitude: Float)