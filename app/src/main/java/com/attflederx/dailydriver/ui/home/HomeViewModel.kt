package com.attflederx.dailydriver.ui.home

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.attflederx.dailydriver.domain.NewsModel
import com.attflederx.dailydriver.domain.WeatherModel
import com.attflederx.dailydriver.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class HomeViewModel : ViewModel() {

    // coroutine objects
    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val weatherRepository = WeatherRepository()

    // mock data
    private val _news = MutableLiveData<List<NewsModel>>().apply {
        value = listOf(
            NewsModel(1, "https://placeimg.com/640/480/people", "News item #1", "Description #1", "NBC News", Date(1987, 8, 11),"https://www.nbcnews.com/"),
            NewsModel(2, "https://placeimg.com/640/480/arch", "News item #2", "Description #2", "ABC News", Date(1988, 8, 11),"https://www.abcnews.go.com/"),
            NewsModel(3, "https://placeimg.com/640/480/tech", "News item #3", "Description #3", "CNN", Date(1989, 11, 19),"https://edition.cnn.com/"))
    }
    val news: LiveData<List<NewsModel>> = _news

    private val _weather: MutableLiveData<WeatherModel> = weatherRepository.weatherData

//        .apply {
//        value = WeatherModel(Calendar.getInstance().time, 18.0, "Clear", "Miami", 19.0, 15.0, 0)
//    }
    val weather: LiveData<WeatherModel> = _weather

    init {
        refreshWeather()
    }

    private fun refreshWeather() {
        viewModelScope.launch {
            try {
                weatherRepository.refreshWeather()
            }
            catch (nwex: IOException) {

            }
        }
    }
}