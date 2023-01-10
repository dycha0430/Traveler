package com.example.traveler.model

data class User(
    val name: String,
    val trips: List<TripPlan>
)