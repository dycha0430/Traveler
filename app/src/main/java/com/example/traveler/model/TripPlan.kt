package com.example.traveler.model

import java.util.Date

enum class STATE {
    PREPARING,
    TRAVELING,
    DONE
}

data class TripPlan(
    val title: String,
    val destination: String, // TODO
    val state: STATE,
    val startDate: Date,
    val endDate: Date,
    val participants: List<User>
)