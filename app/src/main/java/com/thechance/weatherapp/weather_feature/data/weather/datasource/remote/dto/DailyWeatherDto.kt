package com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherDto(
    @SerialName("time")
    val days: List<String>,
    @SerialName("weather_code")
    val weatherCodes: List<Int>,
    @SerialName("temperature_2m_min")
    val minTemperatures: List<Double>,
    @SerialName("temperature_2m_max")
    val maxTemperatures: List<Double>
)
