package com.thechance.weatherapp.weather_feature.data.weather


import android.os.Build
import androidx.annotation.RequiresApi
import com.thechance.weatherapp.core.data.utils.toDate
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.RemoteWeatherDataSource
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.mapper.toCurrentWeatherData
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.mapper.toList
import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate
import com.thechance.weatherapp.weather_feature.domain.weather.WeatherRepository
import com.thechance.weatherapp.weather_feature.domain.weather.model.CurrentWeatherData
import com.thechance.weatherapp.weather_feature.domain.weather.model.DailyWeatherData
import com.thechance.weatherapp.weather_feature.domain.weather.model.HourlyWeatherData
import java.time.LocalDate

class WeatherRepositoryImpl(
    private val remoteWeatherDataSource: RemoteWeatherDataSource
) : WeatherRepository {
    override suspend fun getCurrentWeatherData(locationCoordinate: LocationCoordinate): Result<CurrentWeatherData> {
        return remoteWeatherDataSource.getCurrentWeatherData(locationCoordinate).map {
            it.toCurrentWeatherData()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getHourlyWeatherData(
        locationCoordinate: LocationCoordinate,
        endDate: LocalDate
    ): Result<List<HourlyWeatherData>> {
        return remoteWeatherDataSource.getHourlyWeatherData(locationCoordinate, endDate.toDate())
            .map {
                it.toList()
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDailyWeatherData(locationCoordinate: LocationCoordinate): Result<List<DailyWeatherData>> {
        return remoteWeatherDataSource.getDailyWeatherData(locationCoordinate).map {
            it.toList()
        }
    }
}