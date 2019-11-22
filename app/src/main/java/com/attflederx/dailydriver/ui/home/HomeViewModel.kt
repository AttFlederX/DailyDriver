package com.attflederx.dailydriver.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.attflederx.dailydriver.domain.NewsModel
import com.attflederx.dailydriver.domain.WeatherModel
import com.attflederx.dailydriver.network.models.enums.NetworkStatus
import com.attflederx.dailydriver.repository.NewsRepository
import com.attflederx.dailydriver.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class HomeViewModel : ViewModel() {

    // coroutine objects
    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val weatherRepository = WeatherRepository()
    private val newsRepository = NewsRepository()

    private var _networkStatus = MutableLiveData<NetworkStatus>()
    val networkStatus: LiveData<NetworkStatus>
        get() = _networkStatus

    // data containers
    private val _news: MutableLiveData<List<NewsModel>> = newsRepository.newsData
    val news: LiveData<List<NewsModel>> = _news

    private val _weather: MutableLiveData<WeatherModel> = weatherRepository.weatherData
    val weather: LiveData<WeatherModel> = _weather

    init {
        refreshWeather()
        refreshNews()
    }

    private fun refreshWeather() {
        viewModelScope.launch {
            _networkStatus.value = NetworkStatus.LOADING

            try {
                weatherRepository.refreshWeather()
            }
            catch (nwex: IOException) {
                _networkStatus.value = NetworkStatus.ERROR
            }
        }
    }

    private fun refreshNews() {
        viewModelScope.launch {

            try {
                newsRepository.refreshNews()

                _networkStatus.value = NetworkStatus.DONE
            }
            catch (nwex: IOException) {
                _networkStatus.value = NetworkStatus.ERROR
            }
        }
    }
}