package com.example.traveler.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.databinding.ItemScheduleBinding
import com.example.domain.model.Schedule
import java.time.format.DateTimeFormatter

class SchedulesAdapter constructor(
    private val onClickScheduleListener: OnClickScheduleListener
) : ListAdapter<Schedule, SchedulesAdapter.ViewHolder>(
    object: DiffUtil.ItemCallback<Schedule>() {
        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
        }
    }
) {
    interface OnClickScheduleListener {
        fun onClick(schedule: Schedule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemScheduleBinding = ItemScheduleBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener(View.OnClickListener {
            onClickScheduleListener.onClick(item)
        })
        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            // TODO: delete
            true
        })
    }

    inner class ViewHolder(private val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Schedule) {
            with(binding) {
                val timeFormat = "HH:mm"
                startTimeTextView.text = item.startTime.format(DateTimeFormatter.ofPattern(timeFormat))
                endTimeTextView.text = item.endTime.format(DateTimeFormatter.ofPattern(timeFormat))
                placeTextView.text = item.place.name
                addressTextView.text = item.place.address
                memoTextView.text = item.memo
                costTextView.text = item.cost.toString()
            }
        }
    }
}