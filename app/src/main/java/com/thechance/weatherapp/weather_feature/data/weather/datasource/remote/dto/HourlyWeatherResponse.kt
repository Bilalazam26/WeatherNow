package com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherResponse(
    val hourly: HourlyWeatherDto
)
