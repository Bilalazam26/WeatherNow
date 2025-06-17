package com.thechance.weatherapp.weather_feature.domain.location

import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate

interface LocationProvider {
    suspend fun getCurrentLocation(): Result<LocationCoordinate>
    suspend fun getCityFromLocation(coordinate: LocationCoordinate): Result<String>
}