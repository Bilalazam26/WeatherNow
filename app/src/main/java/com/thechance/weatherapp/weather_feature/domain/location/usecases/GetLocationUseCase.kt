package com.thechance.weatherapp.weather_feature.domain.location.usecases

import com.thechance.weatherapp.weather_feature.domain.location.LocationProvider
import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate

class GetLocationUseCase(
    private val locationProvider: LocationProvider
) {
    suspend operator fun invoke(): Result<LocationCoordinate> =
        locationProvider.getCurrentLocation()
}