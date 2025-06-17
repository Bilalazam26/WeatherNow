package com.thechance.weatherapp.core.data.exception

open class NetworkException(message: String) : Exception(message)

class ServerException(message: String = "Server side error") : NetworkException(message)
class UnknownException(message: String = "Unknown error") : NetworkException(message)
class NoInternetException(message: String = "No internet connection") : NetworkException(message)
class SerializationException(message: String = "Serialization error") : NetworkException(message)
class RequestTimeoutException(message: String = "Request timeout") : NetworkException(message)
class TooManyRequestsException(message: String = "Too many requests") : NetworkException(message)