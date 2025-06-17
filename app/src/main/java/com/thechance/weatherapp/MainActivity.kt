package com.thechance.weatherapp

import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.thechance.weatherapp.core.ui.theme.WeatherAppTheme
import com.thechance.weatherapp.weather_feature.ui.screen.weather.WeatherScreen
import com.thechance.weatherapp.weather_feature.ui.screen.weather.WeatherViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            androidx.compose.material3.Button(
                onClick = { },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                enabled = false,
                shape = MaterialTheme.shapes.extraLarge,
                contentPadding = ButtonDefaults.ContentPadding
            ) { }
            val viewModel = koinViewModel<WeatherViewModel>()
            val launchLocationPermissionRequest = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.getLocation()
                }
            }

            LaunchedEffect(Unit) {
                launchLocationPermissionRequest.launch(
                    arrayOf(
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                    )
                )
            }

            WeatherAppTheme(
                isNight = !viewModel.currentWeather.isDay
            ) {
                WeatherScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }
}