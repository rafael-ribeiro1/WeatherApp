package pt.ipp.isep.weatherapp.data.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import pt.ipp.isep.weatherapp.data.persistence.model.Location
import pt.ipp.isep.weatherapp.data.persistence.dao.LocationDao
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationDao: LocationDao) : ILocationRepository {

    @WorkerThread
    override suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location)
    }

    override val locations : Flow<List<Location>> = locationDao.getLocations()

    override suspend fun existsLocation(location: String) : Boolean = locationDao.existsLocation(location)

    @WorkerThread
    override suspend fun updateLocation(location: Location) {
        locationDao.updateLocation(location)
    }

}