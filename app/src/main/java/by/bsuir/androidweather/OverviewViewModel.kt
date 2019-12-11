package by.bsuir.androidweather

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import by.bsuir.androidweather.network.LocalWeather
import by.bsuir.androidweather.network.WeatherAPI
import by.bsuir.androidweather.util.formatTempValue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    private val _weatherData = MutableLiveData<LocalWeather>()

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    val temperature: LiveData<String> = Transformations.map(_weatherData) { data ->
        formatTempValue(data?.main?.temp?.toString() ?: "")
    }

    val conditions: LiveData<String> = Transformations.map(_weatherData) { data ->
        data?.weather?.getOrNull(0)?.main ?: ""
    }

    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        WeatherAPI.retrofitService.getMinskWeather().enqueue(
            object : Callback<LocalWeather> {
                override fun onFailure(call: Call<LocalWeather>, t: Throwable) {
                    _error.value = "Failed to fetch data: $t"
                }

                override fun onResponse(
                    call: Call<LocalWeather>,
                    response: Response<LocalWeather>
                ) {
                    _error.value = ""
                    _weatherData.value = response.body()

                }

            })
    }
}

