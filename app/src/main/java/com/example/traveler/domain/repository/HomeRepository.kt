package com.example.traveler.domain.repository

import com.example.traveler.model.TripPlan
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAllTripPlans(): Flow<List<TripPlan>>
}