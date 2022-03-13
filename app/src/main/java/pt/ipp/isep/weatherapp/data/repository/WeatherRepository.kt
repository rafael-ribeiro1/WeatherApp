package pt.ipp.isep.weatherapp.data.repository

import pt.ipp.isep.weatherapp.data.api.WeatherDbApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherDbApi: WeatherDbApi) : IWeatherRepository {

    override suspend fun weatherInLocation(location: String) = weatherDbApi.getWeatherInLocation(location)

}