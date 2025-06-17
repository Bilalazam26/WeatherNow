package com.thechance.weatherapp.weather_feature.ui.screen.weather.sections

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thechance.weatherapp.weather_feature.domain.weather.model.HourlyWeatherData
import com.thechance.weatherapp.weather_feature.ui.screen.weather.components.HourlyWeatherCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodayWeatherSection(
    todayWeather: List<HourlyWeatherData>,
    modifier: Modifier = Modifier,
    isDay: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Today",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(start = 16.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(todayWeather) {
                HourlyWeatherCard(
                    hourlyWeather = it,
                    isDay = isDay
                )
            }
        }
    }

}