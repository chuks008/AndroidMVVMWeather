package com.example.mvvweather.presentation.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvweather.R
import com.example.mvvweather.presentation.adapter.WeatherItemPagerAdapter
import kotlinx.android.synthetic.main.weather_pager_container.*

class WeatherListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_pager_container)

        val fragmentPager = WeatherItemPagerAdapter(supportFragmentManager)
        fragmentPager.addFragment(WeatherItemFragment(), "Lagos")
        fragmentPager.addFragment(WeatherItemFragment(), "Accra")
        fragmentPager.addFragment(WeatherItemFragment(), "Chennai")

        weatherPager.adapter = fragmentPager
        weatherPager.offscreenPageLimit = 2

    }
}