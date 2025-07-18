package com.thechance.weatherapp.core.ui.themeswitch.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Moon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFE0E0E0),
                    Color.LightGray
                ),
                center = Offset(size.width / 2f, size.height / 3f),
            ),
            radius = size.minDimension * 0.15f,
            center = Offset(size.width * 0.4f, size.height * 0.2f)
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFE8E8E8),
                    Color.Gray
                ),
                center = Offset(size.width / 1.8f, size.height / 1.4f),
                radius = size.minDimension * 0.8f
            ),
            radius = size.minDimension * 0.25f,
            center = Offset(size.width * 0.35f, size.height * 0.64f)
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFE8E8E8),
                    Color.DarkGray
                ),
                center = Offset(size.width / 1.35f, size.height / 1.15f),
            ),
            radius = size.minDimension * 0.08f,
            center = Offset(size.width * 0.7f, size.height * 0.82f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MoonPreview() {
    Moon()
}