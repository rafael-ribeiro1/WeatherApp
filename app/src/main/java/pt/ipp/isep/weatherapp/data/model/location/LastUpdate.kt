package pt.ipp.isep.weatherapp.data.model.location

import androidx.room.ColumnInfo

data class LastUpdate(
    val datetime: Long,
    val comment: String,
    @ColumnInfo(name = "icon_url")
    val iconURL: String,
    @ColumnInfo(name = "temp_c")
    val tempC: Int,
    @ColumnInfo(name = "temp_f")
    val tempF: Int
)
