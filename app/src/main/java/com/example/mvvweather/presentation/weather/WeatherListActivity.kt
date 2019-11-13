package com.example.mvvweather.presentation.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import com.example.mvvweather.R
import com.example.mvvweather.presentation.adapter.WeatherItemPagerAdapter
import kotlinx.android.synthetic.main.weather_pager_container.*

class WeatherListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_pager_container)

        val toolbar = findViewById<Toolbar>(R.id.weatherToolbar)
        setSupportActionBar(toolbar)

        val fragmentPager = WeatherItemPagerAdapter(supportFragmentManager)

        fragmentPager.addFragment(WeatherItemFragment(), "Lagos")
        fragmentPager.addFragment(WeatherItemFragment(), "Accra")
        fragmentPager.addFragment(WeatherItemFragment(), "Chennai")

        supportActionBar?.let {
            cityNameToolbarText.text = fragmentPager.getPageTitle(0)
        }

        weatherPager.adapter = fragmentPager
        weatherPager.offscreenPageLimit = 2

        weatherPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                supportActionBar?.let {
                    cityNameToolbarText.text = fragmentPager.getPageTitle(position)
                }
            }
        })

        tabLayout.setupWithViewPager(weatherPager, true)

        for(i in 0..tabLayout.childCount + 1) {
            tabLayout.getTabAt(i)?.text = ""
        }
    }
}