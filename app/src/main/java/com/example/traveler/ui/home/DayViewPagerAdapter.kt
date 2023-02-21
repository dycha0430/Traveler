package com.example.traveler.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.databinding.ItemDayViewPagerBinding
import com.example.domain.model.DayPlan
import java.time.format.DateTimeFormatter

class DayViewPagerAdapter constructor(
    private val onClickAddScheduleBtnListener: OnClickAddScheduleBtnListener,
    private val onClickScheduleListener: SchedulesAdapter.OnClickScheduleListener
) : ListAdapter<DayPlan, DayViewPagerAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<DayPlan>() {
        override fun areItemsTheSame(oldItem: DayPlan, newItem: DayPlan): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DayPlan, newItem: DayPlan): Boolean {
            return oldItem == newItem
        }
    }
) {
    interface OnClickAddScheduleBtnListener {
        fun onClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDayViewPagerBinding = ItemDayViewPagerBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.scheduleAdapter.submitList(item.schedules)
        // TODO: button callback
    }

    inner class ViewHolder(private val binding: ItemDayViewPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        val scheduleAdapter: SchedulesAdapter = SchedulesAdapter(onClickScheduleListener)
        fun bind(item: DayPlan) {
            with(binding) {
                val dateFormat = "MM/dd (E)"
                dateTextView.text = item.date.format(DateTimeFormatter.ofPattern(dateFormat))
                scheduleRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = scheduleAdapter
                }
                scheduleAddBtn.setOnClickListener {
                    onClickAddScheduleBtnListener.onClick()
                }
            }
        }
    }
}