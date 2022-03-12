package pt.ipp.isep.weatherapp.presentation.locationsscreen

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.isep.weatherapp.data.persistence.model.Location
import pt.ipp.isep.weatherapp.data.repository.LocationRepository
import pt.ipp.isep.weatherapp.data.repository.WeatherRepository
import pt.ipp.isep.weatherapp.utils.RequestResult
import java.lang.Exception

class LocationsScreenViewModel(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    val savedLocations: LiveData<List<Location>> = locationRepository.locations.asLiveData()

    fun weatherInLocation(location: String) = liveData(Dispatchers.IO) {
        try {
            emit(RequestResult.success(data = weatherRepository.weatherInLocation(location)))
        } catch (e: Exception) {
            emit(RequestResult.error(data = null, message = e.message ?: "Error fetching weather"))
        }
    }

    fun addNewLocation(location: Location) = viewModelScope.launch {
        locationRepository.insertLocation(location)
    }

    fun existsLocation(location: String) = liveData(Dispatchers.IO) {
        emit(locationRepository.existsLocation(location))
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