package com.attflederx.dailydriver.network.models

import com.attflederx.dailydriver.domain.NewsModel
import com.squareup.moshi.JsonClass
import org.joda.time.format.DateTimeFormat
import java.util.*

@JsonClass(generateAdapter = true)
data class NewsNetworkModelContainer(val articles: List<NewsNetworkModel>)

@JsonClass(generateAdapter = true)
data class NewsNetworkModel(
    val source: JsonNewsSourceModel,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class JsonNewsSourceModel (
    val id: String?,
    val name: String
)

fun NewsNetworkModelContainer.toDomainModel(): List<NewsModel> {
    return articles.map {
        NewsModel(
            title = it.title,
            description = it.description ?: "",
            time = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")
                .parseDateTime(it.publishedAt),
            imageUrl = it.urlToImage ?: "",
            newsUrl = it.url,
            source = it.source.name
        )
    }
}