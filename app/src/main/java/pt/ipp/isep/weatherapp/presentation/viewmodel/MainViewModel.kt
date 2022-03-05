package pt.ipp.isep.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import pt.ipp.isep.weatherapp.data.repository.WeatherRepository
import pt.ipp.isep.weatherapp.utils.RequestResult
import java.lang.Exception

class MainViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun weatherInLocation(location: String) = liveData(Dispatchers.IO) {
        try {
            emit(RequestResult.success(data = weatherRepository.weatherInLocation(location)))
        } catch (e: Exception) {
            emit(RequestResult.error(data = null, message = e.message ?: "Error fetching weather"))
        }
    }

}

class MainViewModelFactory(private val repository: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}