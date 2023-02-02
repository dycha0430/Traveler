package com.example.traveler.model

import java.time.LocalDateTime
import java.util.Date

enum class STATE(private val state_str: String) {
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
    val destination: Destination,
    val state: STATE,
    val startDate: Date,
    val endDate: Date,
    val participants: List<User>,
    val dayPlans: List<DayPlan>
)

data class Destination(
    val name: String,
    val imageUrl: String
)

data class DayPlan(
    val day: Int,
    val schedules: List<Schedule>
)

data class Schedule(
    val id: Int,
    val cost: Int,
    val memo: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val place: Place
)

data class Place(
    val name: String,
    val address: String
)