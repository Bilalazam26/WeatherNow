package com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.thechance.weatherapp.core.data.utils.weatherCodeToCondition
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.CurrentWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.CurrentWeatherResponse
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.DailyWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.DailyWeatherResponse
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.HourlyWeatherDto
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.dto.HourlyWeatherResponse
import com.thechance.weatherapp.weather_feature.domain.weather.model.CurrentWeatherData
import com.thechance.weatherapp.weather_feature.domain.weather.model.DailyWeatherData
import com.thechance.weatherapp.weather_feature.domain.weather.model.HourlyWeatherData
import java.time.LocalDate
import java.time.LocalDateTime

fun CurrentWeatherDto.toCurrentWeatherData(): CurrentWeatherData {
    return CurrentWeatherData(
        temperature = this.temperature,
        condition = weatherCodeToCondition(this.weatherCode),
        feelsLike = this.feelsLike,
        humidity = this.humidity,
        windSpeed = this.windSpeed,
        rain = this.rain,
        pressure = this.pressure,
        isDay = this.isDay == 1
    )
}

fun CurrentWeatherResponse.toCurrentWeatherDto(): CurrentWeatherDto {
    return this.current
}

fun HourlyWeatherResponse.toHourlyWeatherDto(): HourlyWeatherDto {
    return this.hourly
}

@RequiresApi(Build.VERSION_CODES.O)
fun HourlyWeatherDto.toList(): List<HourlyWeatherData> {
    val list = mutableListOf<HourlyWeatherData>()
    this.hours.forEachIndexed { index, time ->
        list.add(
            HourlyWeatherData(
                hour = LocalDateTime.parse(time),
                temperature = this.temperatures[index],
                condition = weatherCodeToCondition(this.weatherCodes[index])
            )
        )
    }
    return list
}

fun DailyWeatherResponse.toDailyWeatherDto(): DailyWeatherDto {
    return this.daily
}

@RequiresApi(Build.VERSION_CODES.O)
fun DailyWeatherDto.toList(): List<DailyWeatherData> {
    val list = mutableListOf<DailyWeatherData>()
    this.days.forEachIndexed { index, time ->
        list.add(
            DailyWeatherData(
                date = LocalDate.parse(time),
                minTemperature = this.minTemperatures[index],
                maxTemperature = this.maxTemperatures[index],
                condition = weatherCodeToCondition(this.weatherCodes[index])
            )
        )
    }
    return list
}