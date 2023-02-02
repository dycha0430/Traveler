package com.example.traveler.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.traveler.base.BaseFragment
import com.example.traveler.databinding.DetailPlanFragmentBinding
import com.google.android.gms.maps.MapView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPlanFragment : BaseFragment<DetailPlanFragmentBinding, HomeViewModel>() {
    override val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var mapView: MapView
    private lateinit var mapController: MapController

    override fun initUi(savedInstanceState: Bundle?) {
        mapController = MapController(requireContext())
        mapView = binding.map
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(mapController)
    }

    override fun observeUi() {
    }

    override fun loadData() {
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DetailPlanFragmentBinding {
        return DetailPlanFragmentBinding.inflate(inflater, container, false)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }
    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}