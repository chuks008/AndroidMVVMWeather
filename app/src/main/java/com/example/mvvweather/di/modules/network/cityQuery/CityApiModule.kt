package com.example.mvvweather.di.modules.network.cityQuery

import com.example.mvvweather.Constants
import com.example.mvvweather.data.places.CityFinderRepository
import com.example.mvvweather.data.places.CityFinderRepositoryImpl
import com.example.mvvweather.data.places.CityQueryApi
import com.example.mvvweather.di.modules.network.NetworkModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class CityQueryApiModule {

    @Provides
    @Singleton
    @Named(Constants.GEOBYTES_API_NAME)
    fun provideCityQueryApiRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.CITY_QUERY_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideCityQueryApi(@Named(Constants.GEOBYTES_API_NAME) retrofit: Retrofit): CityQueryApi {
        return retrofit.create(CityQueryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCityFinderRepository(cityFindRepository: CityFinderRepositoryImpl): CityFinderRepository {
        return cityFindRepository
    }

}