package pt.ipp.isep.weatherapp.data.repository

import pt.ipp.isep.weatherapp.data.api.WeatherDbApi

class WeatherRepository(private val weatherDbApi: WeatherDbApi) {

    suspend fun weatherInLocation(location: String) = weatherDbApi.getWeatherInLocation(location)

}