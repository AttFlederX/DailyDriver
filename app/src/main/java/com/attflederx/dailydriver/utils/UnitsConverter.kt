package com.attflederx.dailydriver.utils

import com.attflederx.dailydriver.models.enums.TempUnits
import org.joda.time.DateTime
import org.joda.time.Period
import kotlin.math.roundToInt

object UnitsConverter {
    fun tempToString(temp: Double): String {
        return when(SettingsHelper.tempUnits) {
            TempUnits.CELSIUS -> "${(temp - 273.15).roundToInt()}°C"
            TempUnits.FAHRENHEIT -> "${(((temp - 273.15) * 1.8) + 32).roundToInt()}°F"
        }
    }

    fun timeIntervalToNowToString(dt: DateTime): String {
        val curTime = DateTime.now()
        val ldt = dt.toLocalDateTime().toDateTime()

        if (curTime > ldt) {
            val span = Period(ldt.toInstant(), curTime.toInstant())

            when {
                span.weeks > 0 -> {
                    return "${span.weeks} weeks ago"
                }
                span.days > 0 -> {
                    return "${span.days} days ago"
                }
                span.hours > 0 -> {
                    return "${span.hours} hours ago"
                }
                span.minutes > 0 -> {
                    return "${span.minutes} minutes ago"
                }
                span.seconds > 0 -> {
                    return "just now"
                }
            }
        }
        return dt.toString("yyyy-MM-dd HH:mm")
    }
}