package pt.ipp.isep.weatherapp.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherInfo(
    @Json(name = "contact_author")
    val contactAuthor: ContactAuthor,
    @Json(name = "currentConditions")
    val currentConditions: CurrentConditions,
    @Json(name = "data_source")
    val dataSource: String,
    @Json(name = "next_days")
    val nextDays: List<NextDay>,
    @Json(name = "region")
    val region: String
)