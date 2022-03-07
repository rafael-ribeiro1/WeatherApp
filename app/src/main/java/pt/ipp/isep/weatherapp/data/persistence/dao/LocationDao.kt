package pt.ipp.isep.weatherapp.data.persistence.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pt.ipp.isep.weatherapp.data.model.location.Location

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLocation(location: Location)

    @Query("SELECT * FROM Location")
    fun getLocations(): Flow<List<Location>>

    @Query("SELECT EXISTS(SELECT * FROM Location WHERE region = :location)")
    suspend fun existsLocation(location: String): Boolean

    @Update
    suspend fun updateLocation(location: Location)

}