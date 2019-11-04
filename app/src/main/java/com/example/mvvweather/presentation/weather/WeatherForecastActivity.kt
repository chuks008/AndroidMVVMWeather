package com.example.mvvweather.presentation.weather

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvweather.R
import com.example.mvvweather.di.modules.viewModel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WeatherForecastActivity: DaggerAppCompatActivity() {

    private val TAG = WeatherForecastActivity::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: WeatherInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_screen)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(WeatherInfoViewModel::class.java)

        viewModel.fiveDayForecast.observe(this, Observer {forecastList ->

            if(forecastList.isEmpty()) {
                Log.e(TAG, "Error getting forecast for the week")
            } else {
                Log.i(TAG, "Forecast list size: ${forecastList.size}")
                Log.i(TAG, "Forecast list: $forecastList")
            }
        })

        viewModel.setCurrentWeather("Lagos")
    }
}