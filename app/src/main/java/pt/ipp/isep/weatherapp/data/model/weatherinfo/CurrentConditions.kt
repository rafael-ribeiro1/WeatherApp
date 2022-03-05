package pt.ipp.isep.weatherapp.data.model.weatherinfo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentConditions(
    @Json(name = "comment")
    val comment: String,
    @Json(name = "dayhour")
    val dayhour: String,
    @Json(name = "humidity")
    val humidity: String,
    @Json(name = "iconURL")
    val iconURL: String,
    @Json(name = "precip")
    val precipitation: String,
    @Json(name = "temp")
    val temp: Temp,
    @Json(name = "wind")
    val wind: Wind
)