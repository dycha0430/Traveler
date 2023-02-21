package com.example.domain.model

data class User(
    val name: String,
    val trips: MutableList<TripPlan>
) {
    constructor() : this("테스트 이름", mutableListOf())
}