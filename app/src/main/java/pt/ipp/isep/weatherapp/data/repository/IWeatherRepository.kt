package pt.ipp.isep.weatherapp.data.repository

import pt.ipp.isep.weatherapp.data.api.model.WeatherInfo

interface IWeatherRepository {

    suspend fun weatherInLocation(location: String) : WeatherInfo

}