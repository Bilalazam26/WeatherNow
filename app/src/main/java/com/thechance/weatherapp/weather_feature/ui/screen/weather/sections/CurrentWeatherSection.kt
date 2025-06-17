package com.thechance.weatherapp.weather_feature.ui.screen.weather.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thechance.weatherapp.weather_feature.ui.screen.weather.components.WeatherAttributeCard
import com.thechance.weatherapp.weather_feature.ui.screen.weather.model.WeatherAttribute

@Composable
fun CurrentWeatherSection(
    modifier: Modifier = Modifier,
    currentWeatherAttributes: List<WeatherAttribute>
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            for (i in 0..currentWeatherAttributes.size / 2 - 1) {
                WeatherAttributeCard(
                    attribute = currentWeatherAttributes[i].attrName,
                    value = currentWeatherAttributes[i].attrValue,
                    measureUnit = currentWeatherAttributes[i].attrMeasureUnit,
                    iconResource = currentWeatherAttributes[i].iconResource,
                    modifier = Modifier.weight(1f)
                )
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            for (i in (currentWeatherAttributes.size / 2)..currentWeatherAttributes.size - 1) {
                WeatherAttributeCard(
                    attribute = currentWeatherAttributes[i].attrName,
                    value = currentWeatherAttributes[i].attrValue,
                    measureUnit = currentWeatherAttributes[i].attrMeasureUnit,
                    iconResource = currentWeatherAttributes[i].iconResource,
                    modifier = Modifier.weight(1f)
                )
            }

        }

    }

}