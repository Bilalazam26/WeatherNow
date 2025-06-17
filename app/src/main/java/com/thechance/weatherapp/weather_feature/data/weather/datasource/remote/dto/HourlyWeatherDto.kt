package com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDto(
    @SerialName("time")
    val hours: List<String>,
    @SerialName("temperature_2m")
    val temperatures: List<Double>,
    @SerialName("weather_code")
    val weatherCodes: List<Int>
)
