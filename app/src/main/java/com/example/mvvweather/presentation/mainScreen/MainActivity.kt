package com.example.mvvweather.presentation.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvweather.R
import com.example.mvvweather.data.location.response.LocationData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainScreenViewModel
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)

        viewModel.fetchLocation().observe(this, Observer<LocationData> {locationData ->
            runOnUiThread {
                Log.i(TAG, "Error occured: ${locationData.error}")
                Log.i(TAG, "City Name ${locationData.city}")
                textView.text = locationData.city
            }
        })
    }
}
