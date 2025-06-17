package com.thechance.weatherapp.weather_feature.ui.screen.weather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thechance.weatherapp.R

@Composable
fun MinMaxTempContainer(
    minTemp: Double,
    maxTemp: Double,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = .08f),
    contentColor: Color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = .87f),
    dividerPadding: Dp = 2.dp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(containerColor)
            .padding(vertical = 8.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_up),
                contentDescription = "temperature",
                tint = contentColor,
                modifier = Modifier.size(12.dp)
            )
            Text(

                text = "${maxTemp.toInt()}°C",
                style = MaterialTheme.typography.bodyLarge,
                color = contentColor
            )

        }

        VerticalDivider(
            thickness = 1.dp,
            color = contentColor.copy(alpha = .2f),
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = dividerPadding)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "temperature",
                tint = contentColor,
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = "${minTemp.toInt()}°C",
                style = MaterialTheme.typography.bodyLarge,
                color = contentColor
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFF35757)
@Composable
fun MinMaxTempContainerPreview() {
    MinMaxTempContainer(minTemp = 25.0, maxTemp = 30.0)
}