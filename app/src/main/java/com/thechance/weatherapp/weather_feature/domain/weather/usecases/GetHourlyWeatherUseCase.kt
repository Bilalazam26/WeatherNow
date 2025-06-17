package com.thechance.weatherapp.weather_feature.domain.weather.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate
import com.thechance.weatherapp.weather_feature.domain.weather.WeatherRepository
import com.thechance.weatherapp.weather_feature.domain.weather.model.HourlyWeatherData
import java.time.LocalDate

class GetHourlyWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(locationCoordinate: LocationCoordinate): Result<List<HourlyWeatherData>> {
        return weatherRepository.getHourlyWeatherData(locationCoordinate, LocalDate.now())
    }
}