package com.attflederx.dailydriver.repository

import androidx.lifecycle.MutableLiveData
import com.attflederx.dailydriver.domain.NewsModel
import com.attflederx.dailydriver.network.NewsDataService
import com.attflederx.dailydriver.network.models.toDomainModel

class NewsRepository {
    val newsData = MutableLiveData<List<NewsModel>>()

    suspend fun refreshNews() {
        val data = NewsDataService.dataService.getTopHeadlinesAsync().await()
        // TODO
        newsData.value = data.toDomainModel()
    }
}