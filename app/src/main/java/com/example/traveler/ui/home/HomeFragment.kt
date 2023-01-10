package com.example.traveler.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.traveler.base.BaseFragment
import com.example.traveler.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {
    override val viewModel by activityViewModels<HomeViewModel>()

    private val allTripPlansAdapter: AllTripPlansAdapter = AllTripPlansAdapter(object : AllTripPlansAdapter.OnClickTripPlanListener {
        override fun onClick(tripPlanIdx: Int) {
            viewModel.clickTripPlan(tripPlanIdx)
            navigateToDetailPlanFragment()
        }
    })

    private fun navigateToDetailPlanFragment() {
        val navDirection: NavDirections = HomeFragmentDirections.actionHomeFragmentToDetailPlanFragment()
        val navController = findNavController()

        navController.navigate(navDirection)
    }

    override fun initUi(savedInstanceState: Bundle?) {
        with(binding) {
            allTripPlanRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = allTripPlansAdapter
            }
        }
    }

    override fun loadData() {
        viewModel.loadAllTripPlans()
    }

    override fun observeUi() {
        with(viewModel) {
            observe(tripPlans) {
                allTripPlansAdapter.submitList(it)
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