package com.example.mvvweather.presentation.adapter.weather_info

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * Created by Your name on 2019-11-04.
 */
data class WeatherDataView(val currentTemp: String = "",
                      val minTemp: String = "",
                      val maxTemp: String = "",
                      val condition: String = "",
                      val conditionIcon: String = "",
                      val city: String = "",
                      val country: String = "")