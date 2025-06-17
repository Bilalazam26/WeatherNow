package com.thechance.weatherapp.weather_feature.ui.screen.weather.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.weatherapp.core.ui.utils.weatherConditionToImage
import com.thechance.weatherapp.weather_feature.domain.weather.model.HourlyWeatherData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherCard(
    hourlyWeather: HourlyWeatherData,
    modifier: Modifier = Modifier,
    isDay: Boolean
) {
    val blurColor = MaterialTheme.colorScheme.surfaceVariant
    val density = LocalDensity.current
    Box(
        modifier = modifier
            .size(88.dp, 132.dp)
    ) {
        Box(
            modifier = Modifier
                .size(88.dp, 120.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = .7f))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .08f),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = 12.dp)
                .padding(bottom = 16.dp)
                .align(BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${hourlyWeather.temperature.toInt()}°C",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .87f)
                )

                Text(
                    text = "${
                        String.format(
                            "%02d",
                            hourlyWeather.hour?.hour
                        )
                    }:${String.format("%02d", hourlyWeather.hour?.minute)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f)
                )
            }
        }


        Image(
            painter = painterResource(id = weatherConditionToImage(hourlyWeather.condition, isDay)),
            contentDescription = hourlyWeather.condition,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(64.dp, 58.dp)
                .graphicsLayer {
                    spotShadowColor = blurColor
                    ambientShadowColor = blurColor
                    shape = CircleShape
                    shadowElevation = with(density) { 50.dp.toPx() }
                }
                .align(TopCenter)
        )

    }
}