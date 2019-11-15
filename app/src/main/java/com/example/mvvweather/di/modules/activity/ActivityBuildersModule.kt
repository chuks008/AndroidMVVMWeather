package com.example.mvvweather.di.modules.activity

import com.example.mvvweather.di.modules.network.cityQuery.CityQueryApiModule
import com.example.mvvweather.di.modules.network.location.LocationModule
import com.example.mvvweather.presentation.StartActivity
import com.example.mvvweather.presentation.mainScreen.MainActivity
import com.example.mvvweather.presentation.newLocation.CityQueryActivity
import com.example.mvvweather.presentation.weather.WeatherActivity
import com.example.mvvweather.presentation.weather.WeatherForecastActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainScreenActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeCityQueryActivity(): CityQueryActivity

    @ContributesAndroidInjector
    abstract fun contributesWeatherActivity(): WeatherActivity

    @ContributesAndroidInjector
    abstract fun contributesWeatherForecastActivity(): WeatherForecastActivity

    @ContributesAndroidInjector
    abstract fun contributesStartActivity(): StartActivity

}