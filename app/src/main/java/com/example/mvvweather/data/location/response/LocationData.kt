package com.example.mvvweather.data.location.response

data class LocationData(val error: Boolean,
                        val city: String,
                        val countryCode: String,
                        val lat: Float,
                        val long: Float)