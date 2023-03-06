package com.example.domain

import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface HomeRepository {
    fun getAllTripPlans(): Flow<List<TripPlan>>
    fun createSchedule(schedule: Schedule) : Flow<Unit>
    fun createTripPlan(tripPlan: TripPlan) : Flow<Unit>
}