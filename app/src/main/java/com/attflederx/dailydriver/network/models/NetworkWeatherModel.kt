package com.attflederx.dailydriver.network.models

import android.opengl.Visibility
import com.attflederx.dailydriver.domain.WeatherModel
import com.squareup.moshi.JsonClass
import java.util.*
import kotlin.math.roundToInt

@JsonClass(generateAdapter = true)
data class NetworkWeatherModel (
    val weather: List<JsonWeatherModel>,
    val main: JsonMainModel,
    val visibility: Double,
    val wind: JsonWindModel,
    val clouds: JsonCloudsModel,
    val dt: Long,
    val sys: JsonSysModel,
    val name: String)

// nested classes
data class JsonWeatherModel (
    val id: Int,
    val main: String,
    val description: String,
    val icon: String)

data class JsonMainModel (
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    val temp_min: Double,
    val temp_max: Double)

data class JsonWindModel (
    val speed: Double,
    val deg: Double)

data class JsonCloudsModel (
    val all: Double)

data class JsonSysModel (
    val country: String,
    val sunrise: Long,
    val sunset: Long)

// mapper methods

fun NetworkWeatherModel.toDomainModel(): WeatherModel {
    return WeatherModel(
        time = Date(dt),
        description = weather.first().main,
        clouds = clouds.all.roundToInt(),
        location = name,
        temp = main.temp,
        temp_min = main.temp_min,
        temp_max = main.temp_max
    )
}