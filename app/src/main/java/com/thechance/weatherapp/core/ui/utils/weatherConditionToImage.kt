package com.thechance.weatherapp.core.ui.utils

import com.thechance.weatherapp.R

fun weatherConditionToImage(condition: String, isDay: Boolean = false): Int {
    return when (condition) {
        "Clear sky" -> if (isDay) R.drawable.clear_sky else R.drawable.clear_sky_night
        "Mainly clear" -> if (isDay) R.drawable.mainly_clear else R.drawable.mainly_clear_night
        "Partly cloudy" -> if (isDay) R.drawable.partly_cloudy else R.drawable.partly_cloudy_night
        "Overcast" -> if (isDay) R.drawable.overcast else R.drawable.overcast_night
        "Fog" -> if (isDay) R.drawable.fog else R.drawable.fog_night
        "Depositing rime fog" -> if (isDay) R.drawable.depositing_rime_fog else R.drawable.depositing_rime_fog_night
        "Light drizzle" -> if (isDay) R.drawable.drizzle_light else R.drawable.drizzle_light_night
        "Moderate drizzle" -> if (isDay) R.drawable.drizzle_moderate else R.drawable.drizzle_moderate_night
        "Dense drizzle" -> if (isDay) R.drawable.drizzle_intensity else R.drawable.drizzle_intensity_night
        "Light freezing drizzle" -> if (isDay) R.drawable.freezing_drizzle_light else R.drawable.freezing_drizzle_light_night
        "Dense freezing drizzle" -> if (isDay) R.drawable.freezing_drizzle_intensity else R.drawable.freezing_drizzle_intensity_night
        "Slight rain" -> if (isDay) R.drawable.rain_slight else R.drawable.rain_slight_night
        "Moderate rain" -> if (isDay) R.drawable.rain_moderate else R.drawable.rain_moderate_night
        "Heavy rain" -> if (isDay) R.drawable.rain_intensity else R.drawable.rain_intensity_night
        "Light freezing rain" -> if (isDay) R.drawable.freezing_light else R.drawable.freezing_light_night
        "Heavy freezing rain" -> if (isDay) R.drawable.freezing_heavy else R.drawable.freezing_heavy_night
        "Slight snow fall" -> if (isDay) R.drawable.rain_slight else R.drawable.rain_slight_night
        "Moderate snow fall" -> if (isDay) R.drawable.snow_fall_moderate else R.drawable.snow_fall_moderate_night
        "Heavy snow fall" -> if (isDay) R.drawable.snow_fall_intensity else R.drawable.snow_fall_intensity_night
        "Snow grains" -> if (isDay) R.drawable.snow_grains else R.drawable.snow_grains_night
        "Slight rain showers" -> if (isDay) R.drawable.rain_shower_slight else R.drawable.rain_shower_slight_night
        "Moderate rain showers" -> if (isDay) R.drawable.rain_shower_moderate else R.drawable.rain_shower_moderate_night
        "Violent rain showers" -> if (isDay) R.drawable.rain_shower_violent else R.drawable.rain_shower_violent_night
        "Slight snow showers" -> if (isDay) R.drawable.snow_shower_slight else R.drawable.snow_shower_slight_night
        "Heavy snow showers" -> if (isDay) R.drawable.snow_shower_heavy else R.drawable.snow_shower_heavy_night
        "Thunderstorm" -> if (isDay) R.drawable.thunderstrom_slight_or_moderate else R.drawable.thunderstrom_slight_or_moderate_night
        "Thunderstorm with slight hail" -> if (isDay) R.drawable.thunderstrom_with_slight_hail else R.drawable.thunderstrom_with_slight_hail_night
        "Thunderstorm with heavy hail" -> if (isDay) R.drawable.thunderstrom_with_heavy_hail else R.drawable.thunderstrom_with_heavy_hail_night
        else -> if (isDay) R.drawable.mainly_clear else R.drawable.mainly_clear_night
    }
}