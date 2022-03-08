package pt.ipp.isep.weatherapp.data.persistence.model

import androidx.room.*

@Entity(tableName = "Location")
data class Location(
    @PrimaryKey(autoGenerate = false)
    val region: String,
    @Embedded
    val lastUpdate: LastUpdate
)
