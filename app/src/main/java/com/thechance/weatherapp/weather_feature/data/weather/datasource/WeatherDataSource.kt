package com.thechance.weatherapp.weather_feature.data.weather.datasource


import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.CurrentWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.DailyWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.HourlyWeatherDto
import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate

abstract class WeatherDataSource {
    abstract suspend fun getCurrentWeatherData(
        locationCoordinate: LocationCoordinate
    ): Result<CurrentWeatherDto>

    abstract suspend fun getHourlyWeatherData(
        locationCoordinate: LocationCoordinate,
        date: String
    ): Result<HourlyWeatherDto>

    abstract suspend fun getDailyWeatherData(
        locationCoordinate: LocationCoordinate
    ): Result<DailyWeatherDto>
}