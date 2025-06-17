package com.thechance.weatherapp.core.data.utils

import com.thechance.weatherapp.core.data.exception.RequestTimeoutException
import com.thechance.weatherapp.core.data.exception.SerializationException
import com.thechance.weatherapp.core.data.exception.ServerException
import com.thechance.weatherapp.core.data.exception.TooManyRequestsException
import com.thechance.weatherapp.core.data.exception.UnknownException
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T> {
    return when (response.status.value) {
        in 200..299 -> { //success
            try {
                Result.success(response.body<T>())
            } catch (_: NoTransformationFoundException) {
                Result.failure(SerializationException())
            }
        }

        408 -> { //timeout
            Result.failure(RequestTimeoutException())
        }

        429 -> { //too many requests
            Result.failure(TooManyRequestsException())
        }

        in 500..599 -> { //server side error
            Result.failure(ServerException())
        }

        else -> Result.failure(UnknownException())
    }
}

//400 -> 499 clint side error
//500 -> 599 server side error
//200 -> 299 success