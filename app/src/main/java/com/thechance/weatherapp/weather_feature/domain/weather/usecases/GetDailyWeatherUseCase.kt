package com.thechance.weatherapp.weather_feature.domain.weather.usecases

import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate
import com.thechance.weatherapp.weather_feature.domain.weather.WeatherRepository
import com.thechance.weatherapp.weather_feature.domain.weather.model.DailyWeatherData

class GetDailyWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(locationCoordinate: LocationCoordinate): Result<List<DailyWeatherData>> {
        return weatherRepository.getDailyWeatherData(locationCoordinate)
    }
}