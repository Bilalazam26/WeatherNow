package com.thechance.weatherapp.weather_feature.ui.screen.weather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.weatherapp.core.ui.theme.BabyBlue

@Composable
fun WeatherAttributeCard(
    attribute: String,
    value: String,
    measureUnit: String,
    iconResource: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(.93f)
            .clip(MaterialTheme.shapes.large)
            .background(
                MaterialTheme.colorScheme.surface.copy(alpha = .7f)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .08f),
                shape = MaterialTheme.shapes.large
            )
            .padding(horizontal = 8.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = attribute,
                tint = BabyBlue,
                modifier = Modifier.size(24.dp)
            )

            Spacer(
                modifier = Modifier
                    .size(8.dp)
            )

            Text(
                text = "$value$measureUnit",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .87f)
            )

            Text(
                text = attribute,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f)
            )
        }

    }

}