package pt.ipp.isep.weatherapp.presentation.locationsscreen

import pt.ipp.isep.weatherapp.data.api.model.WeatherInfo
import pt.ipp.isep.weatherapp.utils.RequestResult

data class LocationWeatherViewState(
    val requestResult: RequestResult<WeatherInfo>? = null,
    val existsLocation: Boolean = false
)
