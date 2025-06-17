package com.thechance.weatherapp.core.data.utils

import com.thechance.weatherapp.core.data.exception.NoInternetException
import com.thechance.weatherapp.core.data.exception.UnknownException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

suspend inline fun <reified T> safeNetworkRequest(
    execute: () -> HttpResponse
): Result<T> {
    val response = try {
        execute()
    } catch (_: UnresolvedAddressException) {
        // the client is unable to resolve the address from the backend,
        // commonly happens when no Internet connection
        return Result.failure(NoInternetException())
    } catch (_: SerializationException) {
        return Result.failure(SerializationException())
    } catch (_: Exception) {
        return Result.failure(UnknownException())
    }
    return responseToResult(response)
}