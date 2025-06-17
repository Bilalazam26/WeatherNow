package com.thechance.weatherapp.weather_feature.domain.weather.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class DailyWeatherData(
    val date: LocalDate? = LocalDate.now(),
    val minTemperature: Double = 0.0,
    val maxTemperature: Double = 0.0,
    val condition: String = ""
)
