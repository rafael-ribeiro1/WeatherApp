package pt.ipp.isep.weatherapp.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "km")
    val km: Int,
    @Json(name = "mile")
    val mile: Int
)