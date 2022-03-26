package com.kaizenplus.weatherapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kaizenplus.weatherapp.R
import com.kaizenplus.weatherapp.data.model.WeatherCity
import com.kaizenplus.weatherapp.databinding.FragmentHomeBinding
import com.kaizenplus.weatherapp.ui.View.doOnDebounceQueryChange
import com.kaizenplus.weatherapp.ui.fragment.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observer()

        binding.etSearch.doOnDebounceQueryChange(viewLifecycleOwner) {
            if (it.isNotBlank()) viewModel.getWeatherSearch(it)
        }


    }


    private fun observer() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner, ::handleLoading)
        viewModel.errorLiveData.observe(viewLifecycleOwner, ::handleError)
        viewModel.successCitiesLiveData.observe(viewLifecycleOwner, ::citiesSuccess)
        viewModel.successSearchLiveData.observe(viewLifecycleOwner, ::searchSuccess)
    }

    private fun citiesSuccess(weatherCities: List<WeatherCity>) {
        homeAdapter.submitList(weatherCities)
    }

    private fun searchSuccess(weatherCity: WeatherCity) {
        Toast.makeText(context, "${weatherCity.city} ${weatherCity.tempC}", Toast.LENGTH_LONG).show()
    }

    private fun handleLoading(flag: Boolean) {
        binding.circularProgressIndicator.isVisible = flag
    }

    private fun handleError(throwable: Throwable) {
        Toast.makeText(context, "{${throwable.message}}", Toast.LENGTH_LONG).show()
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter()
        binding.recyclerView.adapter = homeAdapter
    }
}