package pt.ipp.isep.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import pt.ipp.isep.weatherapp.R
import pt.ipp.isep.weatherapp.databinding.ActivityMainBinding
import pt.ipp.isep.weatherapp.presentation.locationsscreen.LocationsScreenFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}