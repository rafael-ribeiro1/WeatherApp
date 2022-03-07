package pt.ipp.isep.weatherapp.data.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import pt.ipp.isep.weatherapp.data.model.location.Location
import pt.ipp.isep.weatherapp.data.persistence.dao.LocationDao

class LocationRepository(private val locationDao: LocationDao) {

    @WorkerThread
    suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location)
    }

    val locations : Flow<List<Location>> = locationDao.getLocations()

    suspend fun existsLocation(location: String) : Boolean = locationDao.existsLocation(location)

    @WorkerThread
    suspend fun updateLocation(location: Location) {
        locationDao.updateLocation(location)
    }

}