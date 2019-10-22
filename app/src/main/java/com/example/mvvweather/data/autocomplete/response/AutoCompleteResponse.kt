package com.example.mvvweather.data.autocomplete.response

import com.google.gson.annotations.SerializedName

data class AutoCompleteResponse(@SerializedName("predictions")
                                val predictions: List<AutoCompletePrediction>)