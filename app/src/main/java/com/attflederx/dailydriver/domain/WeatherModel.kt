package com.attflederx.dailydriver.domain

import java.util.*

class WeatherModel(val time: Date,
                   val temp: Int,
                   val description: String,
                   val location: String,
                   val temp_min: Int,
                   val temp_max: Int,
                   val clouds: Int) {
    val shortDescription: String
        get() = "$temp°C in $location"

    val dayTemperature: String
        get() = "$temp_max°C / $temp_min°C"

    val cloudsString: String
        get() = "$clouds% clouds"
}