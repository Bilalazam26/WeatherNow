package com.thechance.weatherapp.di

import com.thechance.weatherapp.weather_feature.domain.location.usecases.GetLocationCityUseCase
import com.thechance.weatherapp.weather_feature.domain.location.usecases.GetLocationUseCase
import com.thechance.weatherapp.weather_feature.domain.location.usecases.LocationUseCases
import com.thechance.weatherapp.weather_feature.domain.weather.usecases.GetCurrentWeatherUseCase
import com.thechance.weatherapp.weather_feature.domain.weather.usecases.GetDailyWeatherUseCase
import com.thechance.weatherapp.weather_feature.domain.weather.usecases.GetHourlyWeatherUseCase
import com.thechance.weatherapp.weather_feature.domain.weather.usecases.WeatherUseCases
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetLocationUseCase)
    singleOf(::GetLocationCityUseCase)
    singleOf(::LocationUseCases)

    singleOf(::GetCurrentWeatherUseCase)
    singleOf(::GetDailyWeatherUseCase)
    singleOf(::GetHourlyWeatherUseCase)

    singleOf(::WeatherUseCases)
}