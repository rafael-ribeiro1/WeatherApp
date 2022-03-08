package pt.ipp.isep.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import pt.ipp.isep.weatherapp.DIALOG_PREVIEW_TAG
import pt.ipp.isep.weatherapp.R
import pt.ipp.isep.weatherapp.WeatherApplication
import pt.ipp.isep.weatherapp.data.persistence.model.LastUpdate
import pt.ipp.isep.weatherapp.data.persistence.model.Location
import pt.ipp.isep.weatherapp.data.api.model.WeatherInfo
import pt.ipp.isep.weatherapp.databinding.ActivityMainBinding
import pt.ipp.isep.weatherapp.presentation.adapter.LocationsAdapter
import pt.ipp.isep.weatherapp.presentation.dialog.LocationPreviewDialogFragment
import pt.ipp.isep.weatherapp.presentation.viewmodel.MainViewModel
import pt.ipp.isep.weatherapp.presentation.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        val application = (application as WeatherApplication)
        MainViewModelFactory(application.weatherRepository, application.locationRepository)
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var locationsAdapter: LocationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableSearch()
        setupLocationSearch()
        setupLocationsList()
    }

    private fun setupLocationSearch() {
        binding.btnSearchLocation.setOnClickListener {
            val location = binding.editTextLocation.text.toString()
            if (location.isEmpty()) {
                return@setOnClickListener
            }
            disableSearch()
            viewModel.weatherInLocation(location).observe(this) {
                enableSearch()
                if (it.message != null) {
                    Snackbar.make(
                        binding.btnSearchLocation,
                        getString(R.string.no_location_found),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                val data = it.data ?: return@observe
                viewModel.existsLocation(data.region).observe(this) { exists ->
                    if (exists) {
                        Snackbar.make(
                            binding.btnSearchLocation,
                            getString(R.string.location_already_exists),
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        showLocationPreview(data)
                    }
                }
            }
        }
    }

    private fun showLocationPreview(data: WeatherInfo) {
        val dialog = LocationPreviewDialogFragment(data) {
            val currentConditions = data.currentConditions
            viewModel.addNewLocation(
                Location(
                    data.region, LastUpdate(
                        System.currentTimeMillis(), currentConditions.comment,
                        currentConditions.iconURL, currentConditions.temp.c,
                        currentConditions.temp.f
                    )
                )
            )
        }
        dialog.show(supportFragmentManager, DIALOG_PREVIEW_TAG)
    }

    private fun setupLocationsList() {
        locationsAdapter = LocationsAdapter()
        binding.savedLocationsList.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = locationsAdapter
        }

        viewModel.savedLocations.observe(this) {
            locationsAdapter.submitList(it)
            if (it.isNotEmpty()) {
                binding.tvNoSavedLocation.visibility = View.GONE
            }
        }
    }

    private fun disableSearch() {
        binding.btnSearchLocation.isEnabled = false
        binding.btnSearchLocation.isClickable = false
        binding.btnSearchLocation.isFocusable = false
        binding.editTextLocation.isEnabled = false
        binding.editTextLocation.isFocusable = false
        binding.editTextLocation.isFocusableInTouchMode = false
        binding.editTextLocation.clearFocus()
    }

    private fun enableSearch() {
        binding.btnSearchLocation.isEnabled = true
        binding.btnSearchLocation.isClickable = true
        binding.btnSearchLocation.isFocusable = true
        binding.editTextLocation.isEnabled = true
        binding.editTextLocation.isFocusable = true
        binding.editTextLocation.isFocusableInTouchMode = true
        binding.editTextLocation.setText("")
    }
}