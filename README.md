# 🌤️ Weather Now – Jetpack Compose Weather App

A beautiful, single-screen weather app built using **Kotlin**, **Jetpack Compose**, and **MVVM**, fetching real-time weather data from the [Open-Meteo API](https://open-meteo.com/) using the device's GPS location.


---

## 📦 Download Tudee APK

You can download and install the latest version of Weather Now here:

[⬇️ Download Weather Now APK (v1.0.0)](https://github.com/Bilalazam26/WeatherNow/releases/download/v1.0.0/Weather.Now.apk)

---

## 📸 Demo

<p align="left">

https://github.com/user-attachments/assets/07652aa3-0147-47b6-b119-72524dedbfc2

</p>

---

## 🧠 Key Concepts

This app is a practical demonstration of:

- Jetpack Compose UI
- Clean MVVM Architecture
- Koin for Dependency Injection
- Ktor for Networking
- Location Abstraction Layer
- Google Location Services
- Open-Meteo API Integration
- Single Responsibility & SOLID Principles

---

## 📱 Features

- 📍 Gets current GPS location using **abstraction + Google Location Services**
- ☁️ Fetches weather data from **Open-Meteo API**
- 🖼️ Fully responsive, modern UI using **Jetpack Compose**
- ✅ 100% Figma design implementation
- 💡 Clean architecture with **Separation of Concerns**
- 📦 Modular and scalable codebase with Koin & MVVM

---

## 🛠️ Tech Stack

| Tech                    | Usage                         |
|-------------------------|-------------------------------|
| **Kotlin**              | Programming Language          |
| **Jetpack Compose**     | Declarative UI Framework      |
| **MVVM**                | Architecture Pattern          |
| **Koin**                | Dependency Injection          |
| **Ktor**                | Networking Client             |
| **Google Location API** | GPS Location Provider         |

---
## 🧩 Architecture
<pre>
app
├── core
│   ├── data
│   │   ├── exception
│   │   │   ├── LocationException.kt
│   │   │   └── NetworkException.kt
│   │   └── utils
│   │       ├── hasLocationPermission.kt
│   │       ├── HttpClientFactory.kt
│   │       ├── responseToResult.kt
│   │       ├── safeNetworkRequest.kt
│   │       ├── toDate.kt
│   │       └── weatherCodeToCondition.kt
│   ├── domain
│   └── ui
│       ├── theme
│       │   ├── Color.kt
│       │   ├── Shapes.kt
│       │   ├── Theme.kt
│       │   └── Type.kt
│       ├── themeswitch
│       │   ├── components
│       │   │   ├── Clouds.kt
│       │   │   ├── Moon.kt
│       │   │   └── NightSkyBackground.kt
│       │   └── ThemeSwitcher.kt
│       └── utils
│           └── weatherConditionToImage.kt
├── di
│   ├── dataModule.kt
│   ├── domainModule.kt
│   └── uiModule.kt
├── MainActivity.kt
├── WeatherApp.kt
└── weather_feature
    ├── data
    │   ├── location
    │   │   └── GoogleLocationServiceProvider.kt
    │   └── weather
    │       ├── datasource
    │       │   ├── remote
    │       │   │   ├── dto
    │       │   │   │   ├── CurrentWeatherDto.kt
    │       │   │   │   ├── CurrentWeatherResponse.kt
    │       │   │   │   ├── DailyWeatherDto.kt
    │       │   │   │   ├── DailyWeatherResponse.kt
    │       │   │   │   ├── HourlyWeatherDto.kt
    │       │   │   │   └── HourlyWeatherResponse.kt
    │       │   │   ├── mapper
    │       │   │   │   └── WeatherMapper.kt
    │       │   │   └── RemoteWeatherDataSource.kt
    │       │   └── WeatherDataSource.kt
    │       └── WeatherRepositoryImpl.kt
    ├── domain
    │   ├── location
    │   │   ├── LocationProvider.kt
    │   │   ├── model
    │   │   │   └── LocationCoordinate.kt
    │   │   └── usecases
    │   │       ├── GetLocationCityUseCase.kt
    │   │       ├── GetLocationUseCase.kt
    │   │       └── LocationUseCases.kt
    │   └── weather
    │       ├── model
    │       │   ├── CurrentWeatherData.kt
    │       │   ├── DailyWeatherData.kt
    │       │   └── HourlyWeatherData.kt
    │       ├── usecases
    │       │   ├── GetCurrentWeatherUseCase.kt
    │       │   ├── GetDailyWeatherUseCase.kt
    │       │   ├── GetHourlyWeatherUseCase.kt
    │       │   └── WeatherUseCases.kt
    │       └── WeatherRepository.kt
    └── ui
        └── screen
            └── weather
                ├── components
                │   ├── DailyWeatherCard.kt
                │   ├── HourlyWeatherCard.kt
                │   ├── MinMaxTempContainer.kt
                │   └── WeatherAttributeCard.kt
                ├── model
                │   └── WeatherAttribute.kt
                ├── sections
                │   ├── CurrentWeatherSection.kt
                │   ├── NextWeekWeatherSection.kt
                │   └── TodayWeatherSection.kt
                ├── WeatherScreen.kt
                └── WeatherViewModel.kt

</pre>

---


## 🔧 How to Run

1. Clone the repo:
   ```bash
   git clone https://github.com/Bilalazam26/WeatherNow.git
   
2. Open in Android Studio Arctic Fox (2020.3.1) or newer

3. Make sure Google Location Services is enabled

4. Run the app on a device/emulator with GPS support

## 🔑 API & Permissions
📡 Requires internet access

🧭 Requires fine location permission

🔐 Uses Open-Meteo API (no key required)

---

## 🤝 Contributing
Contributions are welcome! Fork the repo, make your changes, and open a pull request.
