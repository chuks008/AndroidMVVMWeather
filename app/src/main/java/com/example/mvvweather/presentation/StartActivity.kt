package com.example.mvvweather.presentation

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvweather.presentation.newLocation.CityQueryActivity
import com.example.mvvweather.presentation.weather.WeatherListActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class StartActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var startViewModel: StartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(StartViewModel::class.java)

        val intent = if(startViewModel.containsWeatherEntries()) {
            Intent(this, WeatherListActivity::class.java)
        } else {
            Intent(this, CityQueryActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}