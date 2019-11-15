package com.example.mvvweather.data.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class WeatherStoreRepositoryImpl @Inject constructor(val sharedPreferences: SharedPreferences,
                                                     val gson: Gson): WeatherStoreRepository {

    override fun getWeatherEntries(): ArrayList<WeatherStoreEntry> {
        val entries = sharedPreferences.getString("weather_entries", "[]")
        return gson.fromJson(entries, object: TypeToken<List<WeatherStoreEntry>>(){}.type)
    }

    override fun addWeatherEntry(weatherStoreEntry: WeatherStoreEntry): Boolean {
        val weatherEntries = getWeatherEntries().add(weatherStoreEntry)
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
}