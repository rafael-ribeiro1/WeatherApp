package pt.ipp.isep.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import pt.ipp.isep.weatherapp.data.api.WeatherDbApiClient
import pt.ipp.isep.weatherapp.data.persistence.AppDatabase
import pt.ipp.isep.weatherapp.data.repository.LocationRepository
import pt.ipp.isep.weatherapp.data.repository.WeatherRepository

@HiltAndroidApp
class WeatherApplication : Application()