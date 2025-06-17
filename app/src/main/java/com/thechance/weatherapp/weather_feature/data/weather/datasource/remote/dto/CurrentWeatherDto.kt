package com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("time")
    val time: String,
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("relative_humidity_2m")
    val humidity: Int,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("apparent_temperature")
    val feelsLike: Double,
    @SerialName("rain")
    val rain: Double,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("pressure_msl")
    val pressure: Double,
    @SerialName("wind_speed_10m")
    val windSpeed: Double
)
