package com.example.mvvweather.di.modules.network.location

import com.example.mvvweather.Constants
import com.example.mvvweather.data.location.LocationApi
import com.example.mvvweather.data.location.CurrentLocationManager
import com.example.mvvweather.data.location.CurrentLocationManagerImpl
import com.example.mvvweather.di.modules.network.NetworkModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class LocationModule {

    @Provides
    @Singleton
    @Named(Constants.LOCATION_API_NAME)
    fun provideLocationAPIRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.LOCATION_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideLocationAPI(@Named(Constants.LOCATION_API_NAME) retrofit: Retrofit): LocationApi {
        return retrofit.create(LocationApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherLocationManager(manager: CurrentLocationManagerImpl): CurrentLocationManager {
        return manager
    }
}