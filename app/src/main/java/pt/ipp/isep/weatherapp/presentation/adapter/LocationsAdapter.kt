package pt.ipp.isep.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pt.ipp.isep.weatherapp.data.persistence.model.Location
import pt.ipp.isep.weatherapp.databinding.ItemLocationBinding

class LocationsAdapter : ListAdapter<Location, LocationsAdapter.LocationsViewHolder>(DiffCallback()) {

    inner class LocationsViewHolder(private val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            binding.tvItemLocation.text = location.region
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLocationBinding.inflate(inflater, parent, false)
        return LocationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Location>() {

        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.region == newItem.region
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }
}
