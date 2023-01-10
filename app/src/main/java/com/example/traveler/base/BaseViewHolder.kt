package com.example.traveler.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    containerView: View
) : RecyclerView.ViewHolder(containerView) {
    abstract fun bind(item: T)
}