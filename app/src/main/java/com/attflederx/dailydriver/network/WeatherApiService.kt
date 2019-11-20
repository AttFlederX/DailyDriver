package com.attflederx.dailydriver.network

import com.attflederx.dailydriver.network.models.NetworkWeatherModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val OPEN_WEATHER_MAP_API_KEY = "NO_KEY_4_U"

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface WeatherApiService {
    @GET("weather?appid=$OPEN_WEATHER_MAP_API_KEY&q=Miami")
    fun getCurrentWeatherAsync(): Deferred<NetworkWeatherModel>
}

object WeatherDataService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val dataService = retrofit.create(WeatherApiService::class.java)
}