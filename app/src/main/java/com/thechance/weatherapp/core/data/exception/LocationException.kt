package com.thechance.weatherapp.core.data.exception

open class LocationException(message: String) : Exception(message)

class LocationFailureException(message: String = "Failed to get location") :
    LocationException(message)

class LocationPermissionException(message: String = "Location permission not granted") :
    LocationException(message)