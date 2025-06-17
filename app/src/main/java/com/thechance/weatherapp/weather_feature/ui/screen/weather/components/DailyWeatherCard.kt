package com.thechance.weatherapp.weather_feature.ui.screen.weather.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.weatherapp.core.ui.utils.weatherConditionToImage
import com.thechance.weatherapp.weather_feature.domain.weather.model.DailyWeatherData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyWeatherCard(
    modifier: Modifier = Modifier,
    dailyWeather: DailyWeatherData,
    isDay: Boolean
) {
    val blurColor = MaterialTheme.colorScheme.surfaceVariant
    val density = LocalDensity.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(69.dp)
            .padding(vertical = 12.dp),
    ) {
        Text(
            text = dailyWeather.date?.dayOfWeek.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f),
            modifier = Modifier
                .align(Alignment.CenterStart)
        )


        Image(
            painter = painterResource(id = weatherConditionToImage(dailyWeather.condition, isDay)),
            contentDescription = dailyWeather.condition,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .graphicsLayer {
                    spotShadowColor = blurColor
                    ambientShadowColor = blurColor
                    shape = CircleShape
                    shadowElevation = with(density) { 150.dp.toPx() }
                }
                .height(45.dp)
                .align(Alignment.Center)
        )


        MinMaxTempContainer(
            minTemp = dailyWeather.minTemperature,
            maxTemp = dailyWeather.maxTemperature,
            containerColor = Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = .87f),
            dividerPadding = 8.dp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }

}