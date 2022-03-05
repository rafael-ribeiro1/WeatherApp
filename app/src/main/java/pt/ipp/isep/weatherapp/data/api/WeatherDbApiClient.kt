package pt.ipp.isep.weatherapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://weatherdbi.herokuapp.com/"

object WeatherDbApiClient {

    val weatherDbApi by lazy { setup() }

    private fun setup(): WeatherDbApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

}