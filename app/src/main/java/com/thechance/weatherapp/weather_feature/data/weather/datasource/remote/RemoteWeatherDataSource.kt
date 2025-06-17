package com.thechance.weatherapp.weather_feature.data.weather.datasource.remote

import com.thechance.weatherapp.BuildConfig
import com.thechance.weatherapp.core.data.utils.safeNetworkRequest
import com.thechance.weatherapp.weather_feature.data.weather.datasource.WeatherDataSource
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.CurrentWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.CurrentWeatherResponse
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.DailyWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.DailyWeatherResponse
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.HourlyWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.HourlyWeatherResponse
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.mapper.toCurrentWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.mapper.toDailyWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.mapper.toHourlyWeatherDto
import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class RemoteWeatherDataSource(
    private val httpClient: HttpClient
) : WeatherDataSource() {
    override suspend fun getCurrentWeatherData(locationCoordinate: LocationCoordinate): Result<CurrentWeatherDto> {
        return safeNetworkRequest<CurrentWeatherResponse> {
            httpClient.get(urlString = BuildConfig.BASE_URL) {
                parameter("latitude", locationCoordinate.latitude)
                parameter("longitude", locationCoordinate.longitude)
                parameter(
                    "current",
                    "temperature_2m,relative_humidity_2m,is_day,apparent_temperature,rain,weather_code,pressure_msl,wind_speed_10m"
                )
                parameter("timezone", "auto")
            }
        }.map {
            it.toCurrentWeatherDto()
        }
    }

    override suspend fun getHourlyWeatherData(
        locationCoordinate: LocationCoordinate,
        date: String
    ): Result<HourlyWeatherDto> {
        return safeNetworkRequest<HourlyWeatherResponse> {
            httpClient.get(urlString = BuildConfig.BASE_URL) {
                parameter("latitude", locationCoordinate.latitude)
                parameter("longitude", locationCoordinate.longitude)
                parameter(
                    "hourly",
                    "temperature_2m,weather_code"
                )
                parameter("start_date", date)
                parameter("end_date", date)
                parameter("timezone", "auto")
            }
        }.map {
            it.toHourlyWeatherDto()
        }
    }


    override suspend fun getDailyWeatherData(
        locationCoordinate: LocationCoordinate
    ): Result<DailyWeatherDto> {
        return safeNetworkRequest<DailyWeatherResponse> {
            httpClient.get(urlString = BuildConfig.BASE_URL) {
                parameter("latitude", locationCoordinate.latitude)
                parameter("longitude", locationCoordinate.longitude)
                parameter(
                    "daily",
                    "temperature_2m_max,temperature_2m_min,weather_code"
                )
                parameter("timezone", "auto")
            }
        }.map {
            it.toDailyWeatherDto()
        }
    }
}