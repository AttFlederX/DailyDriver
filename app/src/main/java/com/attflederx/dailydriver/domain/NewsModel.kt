package com.attflederx.dailydriver.domain

import org.joda.time.format.DateTimeFormat
import java.time.format.DateTimeFormatter
import java.util.*

class NewsModel(val newsId: Long,
                val imageUrl: String,
                val title: String,
                val description: String,
                val source: String,
                val time: Date,
                val newsUrl: String) {
    val timeString: String
        get() = "3 hours ago" // TODO
}