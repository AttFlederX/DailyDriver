package com.attflederx.dailydriver.domain

import com.attflederx.dailydriver.utils.UnitsConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.time.format.DateTimeFormatter
import java.util.*

class NewsModel(val imageUrl: String,
                val title: String,
                val description: String,
                val source: String,
                val time: DateTime,
                val newsUrl: String) {
    val timeString: String
        get() = UnitsConverter.timeIntervalToNowToString(time)// TODO
}