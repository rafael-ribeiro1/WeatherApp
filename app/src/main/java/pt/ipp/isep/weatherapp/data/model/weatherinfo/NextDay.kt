package pt.ipp.isep.weatherapp.data.model.weatherinfo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NextDay(
    @Json(name = "comment")
    val comment: String,
    @Json(name = "day")
    val day: String,
    @Json(name = "iconURL")
    val iconURL: String,
    @Json(name = "max_temp")
    val maxTemp: Temp,
    @Json(name = "min_temp")
    val minTemp: Temp
)