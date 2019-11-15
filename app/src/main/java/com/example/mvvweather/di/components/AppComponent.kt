package com.example.mvvweather.di.components

import android.app.Application
import com.example.mvvweather.application.MVVWeatherApp
import com.example.mvvweather.di.modules.AppModule
import com.example.mvvweather.di.modules.activity.ActivityBuildersModule
import com.example.mvvweather.di.modules.network.cityQuery.CityQueryApiModule
import com.example.mvvweather.di.modules.network.location.LocationModule
import com.example.mvvweather.di.modules.network.weather.WeatherApiModule
import com.example.mvvweather.di.modules.viewModel.ViewModelFactoryModule
import com.example.mvvweather.di.modules.viewModel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ViewModelFactoryModule::class,
    ViewModelModule::class,
    ActivityBuildersModule::class,
    LocationModule::class,
    CityQueryApiModule::class,
    WeatherApiModule::class,
    AppModule::class
    ])
interface AppComponent: AndroidInjector<MVVWeatherApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

}