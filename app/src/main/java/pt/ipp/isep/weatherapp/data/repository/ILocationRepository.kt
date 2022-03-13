package pt.ipp.isep.weatherapp.data.repository

import kotlinx.coroutines.flow.Flow
import pt.ipp.isep.weatherapp.data.persistence.model.Location

interface ILocationRepository {

    suspend fun insertLocation(location: Location)

    val locations : Flow<List<Location>>

    suspend fun existsLocation(location: String) : Boolean

    suspend fun updateLocation(location: Location)

}