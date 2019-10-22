package com.example.mvvweather.data.autocomplete.response

import com.google.gson.annotations.SerializedName

data class AutoCompletePrediction(@SerializedName("description") val description: String,
                                  @SerializedName("place_id") val placeId: String)