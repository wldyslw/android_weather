package by.bsuir.androidweather.network

import com.squareup.moshi.Json

data class LocalWeatherMain(
    val temp: Int,
    val pressure: Int,
    val humidity: Int
)

data class LocalWeatherWeather(
    val description: String,
    val main: String
)

data class LocalWeather(
    val main: LocalWeatherMain?,
    val weather: List<LocalWeatherWeather?>
)
