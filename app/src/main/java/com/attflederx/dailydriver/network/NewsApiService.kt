package com.attflederx.dailydriver.network

import com.attflederx.dailydriver.network.models.NewsNetworkModelContainer
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val NEWS_API_KEY = "NO_KEY_4_U"

private const val BASE_URL = "https://newsapi.org/v2/"

interface NewsApiService {
    @GET("top-headlines?country=us&apiKey=$NEWS_API_KEY")
    fun getTopHeadlinesAsync(): Deferred<NewsNetworkModelContainer>
}

object NewsDataService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val dataService = retrofit.create(NewsApiService::class.java)
}