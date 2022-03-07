package pt.ipp.isep.weatherapp

import android.app.Application
import pt.ipp.isep.weatherapp.data.api.WeatherDbApiClient
import pt.ipp.isep.weatherapp.data.persistence.AppDatabase
import pt.ipp.isep.weatherapp.data.repository.LocationRepository
import pt.ipp.isep.weatherapp.data.repository.WeatherRepository

class WeatherApplication : Application() {

    val weatherRepository by lazy { WeatherRepository(WeatherDbApiClient.weatherDbApi) }

    val locationRepository by lazy {
        LocationRepository(AppDatabase.getDatabase(this).locationDao())
    }

}