package com.example.mvvweather.presentation.weather

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvweather.R
import com.example.mvvweather.data.weather.mapping.WeatherData
import com.example.mvvweather.di.modules.viewModel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.weather_screen.*
import javax.inject.Inject

class WeatherActivity: DaggerAppCompatActivity() {

    private val TAG = WeatherActivity::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: WeatherInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.weather_screen))

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(WeatherInfoViewModel::class.java)

        viewModel.currentWeather.observe(this, Observer<WeatherData> { data ->
            if(data.currentTemp == "") {
                Toast.makeText(this,
                    "Error getting weather data",
                    Toast.LENGTH_SHORT).show()

                currentTempText.text = "-"
                maxTempText.text = "-"
                minTempText.text = "-"
                conditionText.text = "N/A"
            } else {

                currentTempText.text = data.currentTemp
                maxTempText.text = data.maxTemp
                minTempText.text = data.minTemp
                conditionText.text = data.condition
            }
        })

        viewModel.setCurrentWeather("Lagos")
    }
}