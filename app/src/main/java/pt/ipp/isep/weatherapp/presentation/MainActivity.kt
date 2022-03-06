package pt.ipp.isep.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import pt.ipp.isep.weatherapp.DIALOG_PREVIEW_TAG
import pt.ipp.isep.weatherapp.R
import pt.ipp.isep.weatherapp.WeatherApplication
import pt.ipp.isep.weatherapp.databinding.ActivityMainBinding
import pt.ipp.isep.weatherapp.presentation.dialog.LocationPreviewDialogFragment
import pt.ipp.isep.weatherapp.presentation.viewmodel.MainViewModel
import pt.ipp.isep.weatherapp.presentation.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as WeatherApplication).weatherRepository)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableSearch()
        setupLocationSearch()
    }

    private fun setupLocationSearch() {
        binding.btnSearchLocation.setOnClickListener {
            val location = binding.editTextLocation.text.toString()
            if (location.isEmpty()) { return@setOnClickListener }
            disableSearch()
            viewModel.weatherInLocation(location).observe(this) {
                enableSearch()
                if (it.message != null) {
                    Snackbar.make(binding.btnSearchLocation, getString(R.string.no_location_found), Snackbar.LENGTH_LONG)
                        .show()
                }
                val data = it.data ?: return@observe
                val dialog = LocationPreviewDialogFragment(data)
                dialog.show(supportFragmentManager, DIALOG_PREVIEW_TAG)
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