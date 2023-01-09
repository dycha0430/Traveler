package com.example.traveler.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.traveler.base.BaseFragment
import com.example.traveler.databinding.DetailPlanFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPlanFragment : BaseFragment<DetailPlanFragmentBinding, HomeViewModel>() {
    override val viewModel by activityViewModels<HomeViewModel>()

    override fun initUi() {
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
}