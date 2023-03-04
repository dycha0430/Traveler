package com.example.domain.model

data class User(
    val id: String,
    val name: String,
    val tripPlanIds: List<String>
) {
    constructor() : this("", "테스트 이름", mutableListOf())
}