package pt.ipp.isep.weatherapp.presentation.locationsscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pt.ipp.isep.weatherapp.DIALOG_PREVIEW_TAG
import pt.ipp.isep.weatherapp.R
import pt.ipp.isep.weatherapp.WeatherApplication
import pt.ipp.isep.weatherapp.data.api.model.WeatherInfo
import pt.ipp.isep.weatherapp.data.persistence.model.LastUpdate
import pt.ipp.isep.weatherapp.data.persistence.model.Location
import pt.ipp.isep.weatherapp.databinding.FragmentLocationsScreenBinding
import pt.ipp.isep.weatherapp.presentation.adapter.LocationsAdapter
import pt.ipp.isep.weatherapp.presentation.dialog.LocationPreviewDialogFragment

@AndroidEntryPoint
class LocationsScreenFragment : Fragment() {

    private val viewModel: LocationsScreenViewModel by viewModels()

    private lateinit var binding: FragmentLocationsScreenBinding

    private lateinit var locationsAdapter: LocationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        enableSearch()
        setupLocationSearch()
        setupLocationsList()
    }

    private fun setupLocationSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.requestResult.collect {
                    enableSearch()
                    val info = it.requestResult ?: return@collect
                    if (info.message != null) {
                        Snackbar.make(
                            binding.btnSearchLocation,
                            getString(R.string.no_location_found),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    val data = info.data ?: return@collect
                    if (it.existsLocation) {
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
        binding.btnSearchLocation.setOnClickListener {
            val location = binding.editTextLocation.text.toString()
            if (location.isEmpty()) {
                return@setOnClickListener
            }
            disableSearch()
            viewModel.weatherInLocation(location)
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
        activity?.supportFragmentManager?.let { dialog.show(it, DIALOG_PREVIEW_TAG) }
    }

    private fun setupLocationsList() {
        locationsAdapter = LocationsAdapter()
        binding.savedLocationsList.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = locationsAdapter
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.savedLocations.collect {
                    locationsAdapter.submitList(it)
                    if (it.isNotEmpty()) {
                        binding.tvNoSavedLocation.visibility = View.GONE
                    }
                }
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