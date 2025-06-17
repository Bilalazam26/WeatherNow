package com.thechance.weatherapp.weather_feature.ui.screen.weather.sections

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.thechance.weatherapp.weather_feature.domain.weather.model.DailyWeatherData
import com.thechance.weatherapp.weather_feature.ui.screen.weather.components.DailyWeatherCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NextWeekWeatherSection(
    modifier: Modifier = Modifier,
    dailyWeather: List<DailyWeatherData>,
    isDay: Boolean
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Next 7 days",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(start = 16.dp)
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = .7f))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .08f),
                    shape = MaterialTheme.shapes.large
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),

                ) {
                dailyWeather.forEachIndexed { index, dailyWeatherData ->
                    DailyWeatherCard(
                        dailyWeather = dailyWeatherData,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        isDay = isDay
                    )
                    if (index < dailyWeather.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth(),
                            thickness = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .08f)
                        )
                    }
                }
            }
        }
    }


}