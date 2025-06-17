package com.thechance.weatherapp.weather_feature.data.location

import android.app.Application
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.thechance.weatherapp.core.data.exception.LocationFailureException
import com.thechance.weatherapp.core.data.exception.LocationPermissionException
import com.thechance.weatherapp.core.data.utils.hasLocationPermission
import com.thechance.weatherapp.weather_feature.domain.location.LocationProvider
import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import kotlin.coroutines.resume

class GoogleLocationServiceProvider(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationProvider {
    @androidx.annotation.RequiresPermission(allOf = [android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION])
    override suspend fun getCurrentLocation(): Result<LocationCoordinate> {
        if (!hasLocationPermission(application)) {
            return Result.failure(LocationPermissionException())
        }

        return suspendCancellableCoroutine { continuation ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        continuation.resume(
                            Result.success(
                                LocationCoordinate(
                                    latitude = result.latitude,
                                    longitude = result.longitude
                                )
                            )
                        )
                    } else {
                        continuation.resume(
                            Result.failure(
                                LocationFailureException()
                            )
                        )
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    if (it == null) {
                        continuation.resume(
                            Result.failure(
                                LocationFailureException()
                            )
                        )
                        return@addOnSuccessListener
                    }
                    continuation.resume(
                        Result.success(
                            LocationCoordinate(
                                latitude = it.latitude,
                                longitude = it.longitude
                            )
                        )
                    )
                }
                addOnFailureListener {
                    continuation.resume(
                        Result.failure(
                            LocationFailureException()
                        )
                    )
                }
                addOnCanceledListener {
                    continuation.cancel()
                }
            }
        }
    }

    @androidx.annotation.RequiresPermission(allOf = [android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION])
    override suspend fun getCityFromLocation(coordinate: LocationCoordinate): Result<String> {
        return try {
            val geocoder = Geocoder(application, Locale.getDefault())
            val addresses = geocoder.getFromLocation(coordinate.latitude, coordinate.longitude, 1)

            if (!addresses.isNullOrEmpty()) {
                val city = addresses[0].locality
                if (city != null) {
                    Result.success(city)
                } else {
                    Result.failure(LocationFailureException("City not found"))
                }
            } else {
                Result.failure(LocationFailureException("No address found"))
            }
        } catch (e: Exception) {
            Result.failure(LocationFailureException("Geocoding failed: ${e.message}"))
        }
    }
}