package com.example.domain.model

import java.time.LocalDateTime
import java.util.*

enum class STATE(private val state_str: String) {
    PREPARING("여행 준비중"),
    TRAVELING("여행 중"),
    DONE("여행 완료");

    override fun toString(): String {
        return state_str
    }
}

data class TripPlan(
    val id: String,
    val title: String,
    val destination: Destination,
    val state: STATE,
    val participants: List<User>,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime
//    val dayPlans: List<DayPlan>
//    val dayPlanIds: List<String>
//    val participants: List<User>,
//    val dayPlans: List<DayPlan>
) {
}

data class Destination(
    val id: String,
    val name: String,
    val imageUrl: String
) {
    constructor() : this("", "테스트 목적지", "테스트 이미지주소")
}

data class DayPlan(
    val id: String,
    val day: Int,
    val date: LocalDateTime,
    val schedules: List<Schedule>
) {
    constructor(day: Int, date: LocalDateTime) : this("", day, date, mutableListOf(
        Schedule(), Schedule()
    ))
}

data class Schedule(
    var id: String,
    var cost: Int,
    var memo: String,
    var startTime: LocalDateTime,
    var endTime: LocalDateTime,
    var place: Place
) {
    constructor() : this("", 100, "테스트 메모입니다.", LocalDateTime.now(), LocalDateTime.now(), Place())
}

data class Place(
    val name: String,
    val address: String
) {
    constructor() : this("테스트 장소", "테스트 주소")
}