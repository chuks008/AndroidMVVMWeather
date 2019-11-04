package com.example.mvvweather.data.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvweather.data.weather.response.current.WeatherResponse
import com.example.mvvweather.data.weather.mapping.WeatherData
import com.example.mvvweather.data.weather.response.forecast.FiveDayForecastResponse
import com.example.mvvweather.presentation.weather.WeatherQuery
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(private val weatherApi: WeatherApi): WeatherRepository {

    private val TAG = WeatherRepositoryImpl::class.java.simpleName

    override fun getCurrentWeather(weatherQuery: WeatherQuery): LiveData<WeatherData> {

        val currentWeatherData = MutableLiveData<WeatherData>()

        weatherApi.getCurrentWeather(weatherQuery.lat,
            weatherQuery.lon, weatherQuery.city).enqueue(object: Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG, "Failure when getting current weather: ${t.localizedMessage}")
                currentWeatherData.value =
                    WeatherData()
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if(!response.isSuccessful){
                    Log.e(TAG, "Failure when getting current weather on response")
                    currentWeatherData.value =
                        WeatherData()
                } else {
                    Log.i(TAG, "Success getting weather data")
                    val weatherConditions = response.body()?.conditions
                    if(weatherConditions?.get(0) == null) {
                        Log.e(TAG, "No results found")
                        currentWeatherData.value =
                            WeatherData()
                        return
                    }

                    val temps = response.body()!!.temperatures

                    currentWeatherData.value =
                        WeatherData(
                            convertTempToString(temps.currentTemp),
                            convertTempToString(temps.minTemp),
                            convertTempToString(temps.maxTemp),
                            weatherConditions[0].condition,
                            weatherConditions[0].conditionIcon
                        )

                }
            }
        })

        return currentWeatherData
    }

    override fun get5DayForecast(weatherQuery: WeatherQuery): LiveData<List<WeatherData>> {
        val fiveDayData = MutableLiveData<List<WeatherData>>()
        weatherApi.getFiveDayForecast(weatherQuery.city).enqueue(object: Callback<FiveDayForecastResponse> {
            override fun onFailure(call: Call<FiveDayForecastResponse>, t: Throwable) {
                fiveDayData.value = ArrayList<WeatherData>()
            }

            override fun onResponse(
                call: Call<FiveDayForecastResponse>,
                response: Response<FiveDayForecastResponse>
            ) {
                val forecastData = ArrayList<WeatherData>()
                if(!response.isSuccessful) {
                    fiveDayData.value = forecastData
                } else {
                    if(response.body()!!.errorCode != "200") {
                        fiveDayData.value = forecastData
                        return
                    }

                    val hourlyForecast = response.body()!!.weeklyForecast
                    hourlyForecast.forEach {forecast ->
                        forecastData.add(
                            WeatherData(
                                forecast.temperatures.currentTemp.toString(),
                                forecast.temperatures.minTemp.toString(),
                                forecast.temperatures.maxTemp.toString(),
                                forecast.conditions[0].condition,
                                forecast.conditions[0].conditionIcon,
                                forecast.dateTimeMillis
                            )
                        )
                    }

                    fiveDayData.value = forecastData
                }
            }
        })

        return fiveDayData
    }

    private fun convertTempToString(temp: Float): String {
        return String.format("%.2f", temp)
    }
}