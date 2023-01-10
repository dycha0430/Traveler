package com.example.traveler.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.databinding.ItemTripPlanBinding
import com.example.traveler.dateToString
import com.example.traveler.model.TripPlan

class AllTripPlansAdapter constructor(
    private val onClickTripPlanListener: OnClickTripPlanListener
) : ListAdapter<TripPlan, AllTripPlansAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<TripPlan>() {
        override fun areItemsTheSame(oldItem: TripPlan, newItem: TripPlan): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TripPlan, newItem: TripPlan): Boolean {
            return oldItem == newItem
        }

    }
) {
    interface OnClickTripPlanListener {
        fun onClick(tripPlanIdx: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemTripPlanBinding = ItemTripPlanBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener(View.OnClickListener {
            onClickTripPlanListener.onClick(position)
        })
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemTripPlanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TripPlan) {
            with(binding) {
                titleTextView.text = item.title
                destinationTextView.text = item.destination
                assert(item.participants.isNotEmpty())
                if (item.participants.size == 1) {
                    participantsTextView.text = "혼자 여행 중"
                } else {
                    participantsTextView.text = item.participants[0].name + "님 외 " + item.participants.size.toString() + "명"
                }
                stateTextView.text = item.state.toString()
                val dateFormat = "yy.MM.dd"
                val startDate = item.startDate.dateToString(dateFormat)
                val endDate = item.endDate.dateToString(dateFormat)
                dateTextView.text = "$startDate ~ $endDate"
            }
        }
    }
}