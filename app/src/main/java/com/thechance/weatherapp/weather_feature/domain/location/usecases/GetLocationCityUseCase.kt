package com.thechance.weatherapp.weather_feature.domain.location.usecases

import com.thechance.weatherapp.weather_feature.domain.location.LocationProvider
import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate

class GetLocationCityUseCase(
    private val locationProvider: LocationProvider
) {
    suspend operator fun invoke(coordinate: LocationCoordinate): Result<String> {
        return locationProvider.getCityFromLocation(coordinate)

    }
}