package pt.ipp.isep.weatherapp.data.api

import pt.ipp.isep.weatherapp.data.api.model.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Path

private const val LOCATION_PARAM = "location"
private const val WEATHER_PATH = "data/weather/{$LOCATION_PARAM}"

interface WeatherDbApi {

    @GET(WEATHER_PATH)
    suspend fun getWeatherInLocation(@Path(LOCATION_PARAM) location: String): WeatherInfo

}