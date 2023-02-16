package com.example.traveler.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.traveler.databinding.AddScheduleDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.format.DateTimeFormatter

class AddScheduleBottomSheetDialog constructor(private val is_modify: Boolean, private val is_init: Boolean) : BottomSheetDialogFragment() {
    val tagging = "AddScheduleBottomSheetDialog"
    private lateinit var binding: AddScheduleDialogBinding
    private val viewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = AddScheduleDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (is_modify) {
            val schedule = viewModel.selectedSchedule.value!!
            binding.nameEditText.visibility = View.GONE
            binding.addressEditText.visibility = View.GONE

            binding.nameTextView.visibility = View.VISIBLE
            binding.addressTextView.visibility = View.VISIBLE

            binding.nameTextView.text = schedule.place.name
            binding.addressTextView.text = schedule.place.address
            val dateFormat = "hh : mm"
            val startTime = schedule.startTime.format(DateTimeFormatter.ofPattern(dateFormat))

            val endTime = schedule.endTime.format(DateTimeFormatter.ofPattern(dateFormat))
            binding.startTimeBtn.text = startTime
            binding.endTimeBtn.text = endTime
            binding.costEditText.setText(schedule.cost.toString())
            binding.memoTextView.setText(schedule.memo)
        }
        if (is_init) {
            val place = viewModel.selectedPlace.value!!
            binding.nameEditText.setText(place.name)
            binding.addressEditText.setText(place.address)
        }

        binding.registerBtn.setOnClickListener {

        }
    }
}