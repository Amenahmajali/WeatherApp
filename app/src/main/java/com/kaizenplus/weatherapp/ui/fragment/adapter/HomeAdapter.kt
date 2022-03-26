package com.kaizenplus.weatherapp.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kaizenplus.weatherapp.data.model.WeatherCity
import com.kaizenplus.weatherapp.databinding.RowCountryBinding


class HomeAdapter : ListAdapter<WeatherCity, HomeAdapter.HomeViewHolder>(DiffCallback()) {

    inner class HomeViewHolder(private val binding: RowCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherCity: WeatherCity) {
            binding.model = weatherCity
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = RowCountryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class DiffCallback : DiffUtil.ItemCallback<WeatherCity>() {
    override fun areItemsTheSame(
        oldItem: WeatherCity,
        newItem: WeatherCity
    ): Boolean {
        return oldItem.city == newItem.city

    }

    override fun areContentsTheSame(
        oldItem: WeatherCity,
        newItem: WeatherCity
    ): Boolean {
        return oldItem == newItem
    }

}

