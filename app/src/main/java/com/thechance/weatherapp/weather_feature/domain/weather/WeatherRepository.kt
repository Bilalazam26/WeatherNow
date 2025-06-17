package com.thechance.weatherapp.weather_feature.domain.weather

import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate
import com.thechance.weatherapp.weather_feature.domain.weather.model.CurrentWeatherData
import com.thechance.weatherapp.weather_feature.domain.weather.model.DailyWeatherData
import com.thechance.weatherapp.weather_feature.domain.weather.model.HourlyWeatherData
import java.time.LocalDate

interface WeatherRepository {
    suspend fun getCurrentWeatherData(
        locationCoordinate: LocationCoordinate
    ): Result<CurrentWeatherData>

    suspend fun getHourlyWeatherData(
        locationCoordinate: LocationCoordinate,
        endDate: LocalDate
    ): Result<List<HourlyWeatherData>>

    suspend fun getDailyWeatherData(
        locationCoordinate: LocationCoordinate
    ): Result<List<DailyWeatherData>>
}