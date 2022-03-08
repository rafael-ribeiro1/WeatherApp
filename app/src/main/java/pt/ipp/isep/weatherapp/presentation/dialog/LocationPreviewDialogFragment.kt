package pt.ipp.isep.weatherapp.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import pt.ipp.isep.weatherapp.R
import pt.ipp.isep.weatherapp.data.api.model.WeatherInfo

class LocationPreviewDialogFragment(
    private val weatherInfo: WeatherInfo,
    private val saveLocation: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = createView()
            builder.setView(view)
                .setPositiveButton(getString(R.string.save)) { dialog, _ ->
                    saveLocation()
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.cancel()
                }
                .setTitle("${getString(R.string.add)} ${weatherInfo.region}?")
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
        return dialog
    }

    private fun createView() : View {
        val view = View.inflate(context, R.layout.dialog_location_preview, null)
        val currentConditions = weatherInfo.currentConditions
        Glide.with(view) // TODO: placeholder and error
            .load(currentConditions.iconURL)
            .fitCenter()
            .into(view.findViewById(R.id.weather_dialog_image_view))
        view.findViewById<TextView>(R.id.tv_dialog_weather_comment).text = currentConditions.comment
        view.findViewById<TextView>(R.id.tv_dialog_temp_c).text = currentConditions.temp.c.toString()
        view.findViewById<TextView>(R.id.tv_dialog_temp_f).text = currentConditions.temp.f.toString()
        return view
    }

}