package com.example.mvvweather.data.storage

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class WeatherStoreRepositoryImpl @Inject constructor(val sharedPreferences: SharedPreferences,
                                                     val gson: Gson): WeatherStoreRepository {

    private val TAG = WeatherStoreRepositoryImpl::class.java.simpleName

    override fun getWeatherEntries(): ArrayList<WeatherStoreEntry> {
        val entries = sharedPreferences.getString("weather_entries", "[]")
        return gson.fromJson(entries, object: TypeToken<List<WeatherStoreEntry>>(){}.type)
    }

    override fun addWeatherEntry(weatherStoreEntry: WeatherStoreEntry): Boolean {
        val weatherEntries = getWeatherEntries()
        weatherEntries.add(weatherStoreEntry)
        return sharedPreferences
            .edit()
            .putString("weather_entries", gson.toJson(weatherEntries)).commit()
    }

    override fun removeWeatherEntry(cityName: String, lat: Float, lon: Float): Boolean {

        val newWeatherEntry = getWeatherEntries().filterIndexed { _, weatherStoreEntry ->
            weatherStoreEntry.cityName != cityName
        }

        return sharedPreferences
            .edit()
            .putString("weather_entries", gson.toJson(newWeatherEntry)).commit()
    }

    override fun containsEntries(): Boolean {
        return getWeatherEntries().size > 0
    }
}