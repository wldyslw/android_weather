package by.bsuir.androidweather.util

fun formatTempValue(value: String): String {
    return if (value.isEmpty()) "--℃" else "$value℃"
}
