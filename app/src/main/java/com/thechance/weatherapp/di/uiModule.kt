package com.thechance.weatherapp.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.thechance.weatherapp.weather_feature.ui.screen.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val uiModule = module {
    viewModelOf(::WeatherViewModel)
}