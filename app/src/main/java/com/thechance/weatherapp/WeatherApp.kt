package com.thechance.weatherapp

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.thechance.weatherapp.di.dataModule
import com.thechance.weatherapp.di.domainModule
import com.thechance.weatherapp.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class WeatherApp : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApp)
            modules(
                dataModule,
                domainModule,
                uiModule
            )
        }
    }
}