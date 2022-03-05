package pt.ipp.isep.weatherapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pt.ipp.isep.weatherapp.R
import pt.ipp.isep.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}