package com.thechance.weatherapp.weather_feature.ui.screen.weather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.weatherapp.weather_feature.domain.location.model.LocationCoordinate
import com.thechance.weatherapp.weather_feature.domain.location.usecases.LocationUseCases
import com.thechance.weatherapp.weather_feature.domain.weather.model.CurrentWeatherData
import com.thechance.weatherapp.weather_feature.domain.weather.model.DailyWeatherData
import com.thechance.weatherapp.weather_feature.domain.weather.model.HourlyWeatherData
import com.thechance.weatherapp.weather_feature.domain.weather.usecases.WeatherUseCases
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class WeatherViewModel(
    private val weatherUseCases: WeatherUseCases,
    private val locationUseCases: LocationUseCases
) : ViewModel() {
    var location by mutableStateOf(LocationCoordinate())
        private set
    var city by mutableStateOf("Damietta")
        private set

    var currentWeather by mutableStateOf(CurrentWeatherData())
        private set
    var hourlyWeather by mutableStateOf(listOf<HourlyWeatherData>())
        private set
    var dailyWeather by mutableStateOf(listOf<DailyWeatherData>())
        private set

    var isLoading by mutableStateOf(true)
        private set
    var isRefreshing by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set


    fun getLocation() = viewModelScope.launch {
        startLoading()
        locationUseCases.getLocationUseCase()
            .onSuccess {
                location = it
                isRefreshing = false
                isLoading = false
                getLocationCity()
                getCurrentWeather()
                getDailyWeather()
                getHourlyWeather()
            }
            .onFailure {
                error = it.message
                isLoading = false
            }
    }

    fun refresh() = viewModelScope.launch {
        startRefreshing()
        getLocation()
    }

    private fun getLocationCity() = viewModelScope.launch {
        startLoading()
        locationUseCases.getLocationCityUseCase(location)
            .onSuccess {
                city = it
                isLoading = false
            }
            .onFailure {
                error = it.message
                isLoading = false
            }
    }

    private fun getCurrentWeather() = viewModelScope.launch {

        startLoading()
        weatherUseCases.getCurrentWeatherUseCase(location)
            .onSuccess {
                currentWeather = it
                isLoading = false
            }
            .onFailure {
                error = it.message
                isLoading = false
            }
    }

    private fun getHourlyWeather() = viewModelScope.launch {
        startLoading()
        weatherUseCases.getHourlyWeatherUseCase(location)
            .onSuccess {
                hourlyWeather = it
                isLoading = false
            }
            .onFailure {
                error = it.message
                isLoading = false
            }
    }

    private fun getDailyWeather() = viewModelScope.launch {
        startLoading()
        weatherUseCases.getDailyWeatherUseCase(location)
            .onSuccess {
                dailyWeather = it
                isLoading = false
            }
            .onFailure {
                error = it.message
                isLoading = false
            }
    }

    private fun startLoading() {
        isLoading = true
    }

    private fun startRefreshing() {
        isRefreshing = true
    }

}