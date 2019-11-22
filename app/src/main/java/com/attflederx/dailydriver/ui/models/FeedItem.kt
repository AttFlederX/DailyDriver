package com.attflederx.dailydriver.ui.models

import com.attflederx.dailydriver.domain.NewsModel
import com.attflederx.dailydriver.domain.WeatherModel

sealed class FeedItem {
    abstract val id: Long

    data class WeatherItem(val weather: WeatherModel): FeedItem() {
        override val id = Long.MIN_VALUE
    }

    data class NewsItem(val news: NewsModel): FeedItem() {
        override val id = Long.MAX_VALUE
    }
}