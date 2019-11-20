package com.attflederx.dailydriver.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.attflederx.dailydriver.domain.WeatherModel
import com.attflederx.dailydriver.network.WeatherDataService
import com.attflederx.dailydriver.network.models.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository {
    val weatherData = MutableLiveData<WeatherModel>()

    suspend fun refreshWeather() {
        //withContext(Dispatchers.IO) {
            val data = WeatherDataService.dataService.getCurrentWeatherAsync().await()
            // TODO
            weatherData.value = data.toDomainModel()
        //}
    }
}