package com.example.traveler.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.traveler.base.BaseFragment
import com.example.traveler.base.observeEvent
import com.example.traveler.databinding.HomeFragmentBinding
import com.example.traveler.model.TripPlan
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {
    override val viewModel by activityViewModels<HomeViewModel>()

    private fun renderTripPlans(tripPlans: List<TripPlan>) {
        TODO()
    }

    override fun loadData() {
        viewModel.loadAllTripPlans()
    }

    override fun observeUi() {
        with(viewModel) {
            observe(tripPlans) {
                renderTripPlans(it)
            }

            observeEvent(loadingEvent) {
                if (it) showLoadingBar()
                else hideLoadingBar()
            }
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater, container, false)
    }
}