package com.example.traveler.model

import com.example.traveler.R
import java.util.Date
import android.os.Bundle

enum class STATE (private val state_str: String) {
    PREPARING("여행 준비중"),
    TRAVELING("여행 중"),
    DONE("여행 완료");

    override fun toString(): String {
        return state_str
    }
}

data class TripPlan(
    val id: Int,
    val title: String,
    val destination: Destination, // TODO
    val state: STATE,
    val startDate: Date,
    val endDate: Date,
    val participants: List<User>
)

data class Destination(
    val name: String,
    val imageUrl: String,
)