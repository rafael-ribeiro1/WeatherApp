package pt.ipp.isep.weatherapp.presentation.locationsscreen

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pt.ipp.isep.weatherapp.data.api.model.WeatherInfo
import pt.ipp.isep.weatherapp.data.persistence.model.Location
import pt.ipp.isep.weatherapp.data.repository.LocationRepository
import pt.ipp.isep.weatherapp.data.repository.WeatherRepository
import pt.ipp.isep.weatherapp.utils.RequestResult
import java.lang.Exception

class LocationsScreenViewModel(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    val savedLocations: Flow<List<Location>> = locationRepository.locations

    private val _requestResult = MutableStateFlow(LocationWeatherViewState())
    val requestResult = _requestResult.asStateFlow()

    fun weatherInLocation(location: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val reqResult = RequestResult.success(data = weatherRepository.weatherInLocation(location))
            val exists = locationRepository.existsLocation(location)
            _requestResult.value = LocationWeatherViewState(reqResult, exists)
        } catch (e: Exception) {
            _requestResult.value =
                LocationWeatherViewState(RequestResult.error(
                    data = null,
                    message = e.message ?: "Error fetching weather"
                ))
        }
    }

    fun addNewLocation(location: Location) = viewModelScope.launch {
        locationRepository.insertLocation(location)
    }

}

class LocationsScreenViewModelFactory(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationsScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationsScreenViewModel(weatherRepository, locationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}