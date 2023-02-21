package com.example.domain

import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllTripPlans(): Flow<List<TripPlan>>
    fun createSchedule(schedule: Schedule) : Flow<Unit>
}