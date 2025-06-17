package com.thechance.weatherapp.di

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.thechance.weatherapp.core.data.utils.HttpClientFactory
import com.thechance.weatherapp.weather_feature.data.location.GoogleLocationServiceProvider
import com.thechance.weatherapp.weather_feature.data.weather.WeatherRepositoryImpl
import com.thechance.weatherapp.weather_feature.data.weather.datasource.remote.RemoteWeatherDataSource
import com.thechance.weatherapp.weather_feature.domain.location.LocationProvider
import com.thechance.weatherapp.weather_feature.domain.weather.WeatherRepository
import io.ktor.client.engine.cio.CIO
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    single<FusedLocationProviderClient> {
        LocationServices.getFusedLocationProviderClient(androidApplication())
    }

    single<LocationProvider> {
        GoogleLocationServiceProvider(
            locationClient = get(),
            application = androidApplication()
        )
    }

    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteWeatherDataSource)
    single<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }
}