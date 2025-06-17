package com.thechance.weatherapp.weather_feature.ui.screen.weather

import android.content.res.Configuration
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.thechance.weatherapp.R
import com.thechance.weatherapp.core.ui.theme.WeatherAppTheme
import com.thechance.weatherapp.core.ui.utils.weatherConditionToImage
import com.thechance.weatherapp.weather_feature.ui.screen.weather.components.MinMaxTempContainer
import com.thechance.weatherapp.weather_feature.ui.screen.weather.model.WeatherAttribute
import com.thechance.weatherapp.weather_feature.ui.screen.weather.sections.CurrentWeatherSection
import com.thechance.weatherapp.weather_feature.ui.screen.weather.sections.NextWeekWeatherSection
import com.thechance.weatherapp.weather_feature.ui.screen.weather.sections.TodayWeatherSection
import org.koin.androidx.compose.koinViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val error = viewModel.error
    val isError = error != null
    val isLoading = viewModel.isLoading
    val isRefreshing = viewModel.isRefreshing

    val city = viewModel.city
    val currentWeather = viewModel.currentWeather
    val dailyWeather = viewModel.dailyWeather
    val hourlyWeather = viewModel.hourlyWeather

    val blurColor = MaterialTheme.colorScheme.surfaceVariant
    val density = LocalDensity.current

    val scrollState = rememberScrollState()
    val scrollOffset = scrollState.value.coerceAtMost(50)

    val progress = (scrollOffset / 50f).coerceIn(0f, 1f)

    // Weather image animation
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    val maxImageWidth = screenWidthDp - 128.dp
    val maxImageHeight = (maxImageWidth / 1.35f)
    val minImageWidth = screenWidthDp * .5f
    val minImageHeight = (minImageWidth / 1.35f)

    val imageWidth by animateDpAsState(targetValue = maxImageWidth - (maxImageWidth - minImageWidth) * progress)
    val imageHeight by animateDpAsState(targetValue = maxImageHeight - (maxImageHeight - minImageHeight) * progress)

    val imageOffsetX by animateDpAsState(
        targetValue = ((0.dp - ((screenWidthDp - minImageWidth) - (maxImageWidth - minImageWidth)) - 12.dp) * progress),
        tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )
    val infoOffsetX by animateDpAsState(
        targetValue = (0.dp + 128.dp * progress) - (12.dp * progress),
        tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )
    val infoOffsetY by animateDpAsState(
        targetValue = -((maxImageHeight / 2) - 20.dp) * progress,
        tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )

    val initialHeaderHeight = maxImageHeight + 24.dp + 24.dp + 168.dp
    val headerHeight by animateDpAsState(
        targetValue = initialHeaderHeight - (((maxImageHeight - minImageHeight) + 160.dp) * progress),
        tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh() }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds(),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.background,
                                MaterialTheme.colorScheme.surface
                            )
                        )
                    )
            ) {
                AnimatedVisibility(
                    visible = isLoading || isRefreshing,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .systemBarsPadding()
                            .padding(top = 2.dp)
                            .height(8.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                if (isError) {
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                }

                if (!isLoading) {
                    ConstraintLayout(
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .verticalScroll(scrollState)
                            .clipToBounds(),
                    ) {
                        val (header, content) = createRefs()
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(headerHeight)
                                .constrainAs(header) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(top = 24.dp)
                                    .height(24.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.location_ic),
                                    contentDescription = "location",
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    text = city,
                                    modifier = Modifier,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }

                            Image(
                                painter = painterResource(
                                    id = weatherConditionToImage(
                                        currentWeather.condition,
                                        currentWeather.isDay
                                    )
                                ),
                                contentDescription = currentWeather.condition,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .width(imageWidth)
                                    .height(imageHeight)
                                    .padding(top = 12.dp)
                                    .graphicsLayer {
                                        translationX = imageOffsetX.toPx()
                                        spotShadowColor = blurColor
                                        ambientShadowColor = blurColor
                                        shape = CircleShape
                                        shadowElevation = with(density) { 150.dp.toPx() }
                                    }
                            )

                            Column(
                                modifier = Modifier
                                    .width(168.dp)
                                    .wrapContentHeight(unbounded = true)
                                    .graphicsLayer {
                                        translationX = infoOffsetX.toPx()
                                    }
                                    .offset(y = infoOffsetY),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${currentWeather.temperature.toInt()}°C",
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Text(
                                    text = currentWeather.condition,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f),
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )

                                MinMaxTempContainer(
                                    minTemp = dailyWeather.first().minTemperature,
                                    maxTemp = dailyWeather.first().maxTemperature,
                                    modifier = Modifier
                                        .heightIn(max = 35.dp)
                                )

                            }
                        }


                        Column(
                            modifier = Modifier
                                .constrainAs(content) {
                                    top.linkTo(header.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            CurrentWeatherSection(
                                currentWeatherAttributes = listOf<WeatherAttribute>(
                                    WeatherAttribute(
                                        attrName = "Wind",
                                        attrValue = "${currentWeather.windSpeed.toInt()}",
                                        attrMeasureUnit = "km/h",
                                        iconResource = R.drawable.wind_speed_ic
                                    ),
                                    WeatherAttribute(
                                        attrName = "Humidity",
                                        attrValue = "${currentWeather.humidity}",
                                        attrMeasureUnit = "%",
                                        iconResource = R.drawable.humidity_ic
                                    ),
                                    WeatherAttribute(
                                        attrName = "Rain",
                                        attrValue = "${currentWeather.rain.toInt()}",
                                        attrMeasureUnit = "%",
                                        iconResource = R.drawable.rain_ic
                                    ),
                                    WeatherAttribute(
                                        attrName = "UV Index",
                                        attrValue = "2",
                                        attrMeasureUnit = "",
                                        iconResource = R.drawable.uv_ic
                                    ),
                                    WeatherAttribute(
                                        attrName = "Pressure",
                                        attrValue = "${currentWeather.pressure.toInt()}",
                                        attrMeasureUnit = "hPa",
                                        iconResource = R.drawable.pressure_ic
                                    ),
                                    WeatherAttribute(
                                        attrName = "Feels Like",
                                        attrValue = "${currentWeather.feelsLike.toInt()}",
                                        attrMeasureUnit = "°C",
                                        iconResource = R.drawable.feels_like_ic
                                    )
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp)
                            )

                            TodayWeatherSection(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                todayWeather = hourlyWeather,
                                isDay = currentWeather.isDay
                            )

                            NextWeekWeatherSection(
                                dailyWeather = dailyWeather,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                isDay = currentWeather.isDay
                            )
                        }
                    }
                }
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true, showSystemUi = true, device = "id:pixel_5",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeatherScreenNightPreview() {
    WeatherAppTheme {
        WeatherScreen()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true, showSystemUi = true, device = "id:pixel_5"
)
@Composable
fun WeatherScreenPreview() {
    WeatherAppTheme {
        WeatherScreen()
    }
}