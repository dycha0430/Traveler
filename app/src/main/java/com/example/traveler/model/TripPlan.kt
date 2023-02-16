package com.example.traveler.model

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
    val title: String,
    val destination: Destination,
    val state: STATE,
    val participants: List<User>,
    val dayPlans: List<DayPlan>
) {
    val id: UUID = UUID.randomUUID()
    constructor(title: String, destination: Destination, participants: List<User>, startDate: LocalDateTime, days: Int) : this(title, destination, STATE.PREPARING, participants, List<DayPlan>(days) {
        DayPlan(true, it, LocalDateTime.from(startDate).plusDays(it.toLong()))
    })

    constructor(participants: List<User>) : this("테스트 제목", Destination(), STATE.TRAVELING, participants, List<DayPlan>(3) {
        DayPlan(true, it, LocalDateTime.now().plusDays(it.toLong()))
    })

    fun addSchedule(day: Int, schedule: Schedule) {
        assert(day < dayPlans.size)
        dayPlans[day].schedules.add(schedule)
    }
}

data class Destination(
    val name: String,
    val imageUrl: String
) {
    constructor() : this("테스트 목적지", "테스트 이미지주소")
}

data class DayPlan(
    val day: Int,
    val date: LocalDateTime,
    val schedules: MutableList<Schedule>
) {
    val id: UUID = UUID.randomUUID()
    constructor(test: Boolean, day: Int, date: LocalDateTime) : this(day, date, mutableListOf(Schedule(), Schedule()))
}

data class Schedule(
    val cost: Int,
    val memo: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val place: Place
) {
    val id: UUID = UUID.randomUUID()
    constructor() : this(100, "테스트 메모입니다.", LocalDateTime.now(), LocalDateTime.now(), Place())
}

data class Place(
    val name: String,
    val address: String
) {
    constructor() : this("테스트 장소", "테스트 주소")
}