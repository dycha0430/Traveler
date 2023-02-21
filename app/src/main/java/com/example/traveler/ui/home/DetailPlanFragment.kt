package com.example.traveler.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.traveler.base.BaseFragment
import com.example.traveler.databinding.DetailPlanFragmentBinding
import com.example.traveler.getStateColor
import com.example.domain.model.Schedule
import com.google.android.gms.maps.MapView
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailPlanFragment : BaseFragment<DetailPlanFragmentBinding, HomeViewModel>() {
    override val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var mapView: MapView
    private lateinit var mapController: MapController
    private val dayViewPagerAdapter: DayViewPagerAdapter = DayViewPagerAdapter(object: DayViewPagerAdapter.OnClickAddScheduleBtnListener {
        override fun onClick() {
            showScheduleBottomSheetDialog()
        }
    }, object: SchedulesAdapter.OnClickScheduleListener {
        override fun onClick(schedule: Schedule) {
            viewModel.clickSchedule(schedule)
            showScheduleBottomSheetDialog(is_modify = true)
        }
    })

    fun showScheduleBottomSheetDialog(is_modify: Boolean = false, is_init: Boolean = false) {
        val addScheduleBottomSheetDialog = AddScheduleBottomSheetDialog(is_modify, is_init)
        addScheduleBottomSheetDialog.show(requireActivity().supportFragmentManager, addScheduleBottomSheetDialog.tagging)
    }

    override fun initUi(savedInstanceState: Bundle?) {
        mapController = MapController(requireActivity().applicationContext, viewModel.selectedTripPlan.value!!.destination)
        mapView = binding.map
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(mapController)

        with(binding) {
            dayViewPager.apply {
                adapter = dayViewPagerAdapter
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
        }
    }

    override fun observeUi() {
        with(viewModel) {
            observe(selectedTripPlan) {
                dayViewPagerAdapter.submitList(it.dayPlans)
                with(binding) {
                    titleTextView.text = it.title
                    val dateFormat = "MM.dd"
                    val startDate = it.dayPlans.first().date.format(DateTimeFormatter.ofPattern(dateFormat))
                    val endDate = it.dayPlans.last().date.format(DateTimeFormatter.ofPattern(dateFormat))
                    datePickerBtn.text = "$startDate ~ $endDate"
                    stateTextView.text = it.state.toString()
                    stateTextView.setBackgroundColor(getStateColor(binding.root.context, it.state))
                }
            }

            observeEvent(loadingEvent) {
                if (it) showLoadingBar()
                else hideLoadingBar()
            }
        }
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