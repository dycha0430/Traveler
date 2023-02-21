package com.example.traveler.ui.home

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.traveler.R
import com.example.traveler.databinding.AddScheduleDialogBinding
import com.example.domain.model.Schedule
import com.example.traveler.util.NumberTextWatcher
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class AddScheduleBottomSheetDialog constructor(private val is_modify: Boolean, private val is_init: Boolean) : BottomSheetDialogFragment() {
    val tagging = "AddScheduleBottomSheetDialog"
    private lateinit var binding: AddScheduleDialogBinding
    private val viewModel by activityViewModels<HomeViewModel>()
    private lateinit var resultSchedule: Schedule

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        resultSchedule = Schedule()
        binding = AddScheduleDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dateFormat = "HH : mm"
        if (is_modify) {
            val selectedSchedule = viewModel.selectedSchedule.value!!
            resultSchedule = selectedSchedule
            binding.nameEditText.visibility = View.GONE
            binding.addressEditText.visibility = View.GONE

            binding.nameTextView.visibility = View.VISIBLE
            binding.addressTextView.visibility = View.VISIBLE

            binding.nameTextView.text = selectedSchedule.place.name
            binding.addressTextView.text = selectedSchedule.place.address
            val startTime = selectedSchedule.startTime.format(DateTimeFormatter.ofPattern(dateFormat))

            val endTime = selectedSchedule.endTime.format(DateTimeFormatter.ofPattern(dateFormat))
            binding.startTimeBtn.text = startTime
            binding.endTimeBtn.text = endTime
            binding.costEditText.setText(selectedSchedule.cost.toString())
            binding.memoTextView.setText(selectedSchedule.memo)
        }
        else {
            if (is_init) {
                val place = viewModel.selectedPlace.value!!
                binding.nameEditText.setText(place.name)
                binding.addressEditText.setText(place.address)
                resultSchedule.place = place
            }
            val now = LocalDateTime.now()
            binding.startTimeBtn.text = now.format((DateTimeFormatter.ofPattern(dateFormat)))
            binding.endTimeBtn.text = now.format((DateTimeFormatter.ofPattern(dateFormat)))
            resultSchedule.startTime = now
            resultSchedule.endTime = now
        }

        binding.costEditText.addTextChangedListener(NumberTextWatcher(binding.costEditText))

        binding.startTimeBtn.setOnClickListener(View.OnClickListener {
            showTimePickerDialog(true)
        })

        binding.endTimeBtn.setOnClickListener(View.OnClickListener {
            showTimePickerDialog(false)
        })

        binding.registerBtn.setOnClickListener(View.OnClickListener {

        })
    }

    private fun compareTime(hour1: Int, minute1: Int, hour2: Int, minute2: Int): Boolean {
        if (hour1 > hour2 || (hour1 == hour2 && minute1 > minute2)) return true
        return false
    }

    private fun showTimePickerDialog(isStartTime: Boolean) {
        var hour: Int
        var minute: Int
        if (isStartTime) {
            hour = resultSchedule.startTime.hour
            minute = resultSchedule.startTime.minute
        } else {
            hour = resultSchedule.endTime.hour
            minute = resultSchedule.endTime.minute
        }

        val timePickerDialog = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { timePicker, _, _ ->
            val hour = timePicker.hour
            val minute = timePicker.minute
            val startHour = resultSchedule.startTime.hour
            val startMinute = resultSchedule.startTime.minute
            val endHour = resultSchedule.endTime.hour
            val endMinute = resultSchedule.endTime.minute

            if (isStartTime && compareTime(hour, minute, endHour, endMinute)) {
                Toast.makeText(context, requireContext().getString(R.string.error_start_time), Toast.LENGTH_SHORT).show()
            } else if (!isStartTime && compareTime(startHour, startMinute, hour, minute)) {
                Toast.makeText(context, requireContext().getString(R.string.error_end_time), Toast.LENGTH_SHORT).show()
            } else {
                if (isStartTime) {
                    binding.startTimeBtn.text = "$hour : $minute"
                    resultSchedule.startTime = resultSchedule.startTime.withHour(hour).withMinute(minute)
                } else {
                    binding.endTimeBtn.text = "$hour : $minute"
                    resultSchedule.endTime = resultSchedule.endTime.withHour(hour).withMinute(minute)
                }
            }
        }, hour, minute, DateFormat.is24HourFormat(context))

        timePickerDialog.updateTime(hour, minute)
        timePickerDialog.show()
    }
}