package pt.ipp.isep.weatherapp.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Temp(
    @Json(name = "c")
    val c: Int,
    @Json(name = "f")
    val f: Int
)