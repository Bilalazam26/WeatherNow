package com.thechance.weatherapp.weather_feature.domain.weather.usecases

import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate
import com.thechance.weatherapp.weather_feature.domain.weather.WeatherRepository
import com.thechance.weatherapp.weather_feature.domain.weather.model.CurrentWeatherData

class GetCurrentWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(locationCoordinate: LocationCoordinate): Result<CurrentWeatherData> {
        return weatherRepository.getCurrentWeatherData(locationCoordinate)
    }
}