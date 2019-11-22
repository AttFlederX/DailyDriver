package com.attflederx.dailydriver.domain

import com.attflederx.dailydriver.utils.UnitsConverter
import java.util.*

class WeatherModel(val time: Date,
                   val temp: Double,
                   val description: String,
                   val location: String,
                   val temp_min: Double,
                   val temp_max: Double,
                   val clouds: Int) {
    val shortDescription: String
        get() = "${UnitsConverter.tempToString(temp)} in $location"

    val dayTemperature: String
        get() = "${UnitsConverter.tempToString(temp_max)} / ${UnitsConverter.tempToString(temp_min)}"

    val cloudsString: String
        get() = "$clouds% clouds"
}