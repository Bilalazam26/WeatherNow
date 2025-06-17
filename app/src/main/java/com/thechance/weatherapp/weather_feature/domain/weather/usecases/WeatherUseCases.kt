package com.thechance.weatherapp.weather_feature.domain.weather.usecases

data class WeatherUseCases(
    val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    val getDailyWeatherUseCase: GetDailyWeatherUseCase,
    val getHourlyWeatherUseCase: GetHourlyWeatherUseCase
)
