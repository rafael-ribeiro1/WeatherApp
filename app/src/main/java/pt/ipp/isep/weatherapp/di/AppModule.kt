package pt.ipp.isep.weatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pt.ipp.isep.weatherapp.data.api.WeatherDbApi
import pt.ipp.isep.weatherapp.data.api.WeatherDbApiClient
import pt.ipp.isep.weatherapp.data.persistence.AppDatabase
import pt.ipp.isep.weatherapp.data.persistence.dao.LocationDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocationDao(@ApplicationContext context: Context) : LocationDao =
        AppDatabase.getDatabase(context).locationDao()

    @Provides
    @Singleton
    fun provideWeatherDbApi() : WeatherDbApi = WeatherDbApiClient.weatherDbApi

}