package pt.ipp.isep.weatherapp.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import pt.ipp.isep.weatherapp.data.api.WeatherDbApi
import pt.ipp.isep.weatherapp.data.api.WeatherDbApiClient
import pt.ipp.isep.weatherapp.data.persistence.AppDatabase
import pt.ipp.isep.weatherapp.data.persistence.dao.LocationDao
import pt.ipp.isep.weatherapp.data.repository.ILocationRepository
import pt.ipp.isep.weatherapp.data.repository.IWeatherRepository
import pt.ipp.isep.weatherapp.data.repository.LocationRepository
import pt.ipp.isep.weatherapp.data.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun getWeatherRepo(repo: WeatherRepository): IWeatherRepository

    @Binds
    abstract fun getLocationRepo(repo: LocationRepository): ILocationRepository

}