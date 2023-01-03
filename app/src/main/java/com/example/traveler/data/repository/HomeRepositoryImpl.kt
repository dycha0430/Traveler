package com.example.traveler.data.repository

import com.example.traveler.domain.repository.HomeRepository
import com.example.traveler.model.STATE
import com.example.traveler.model.TripPlan
import com.example.traveler.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

val user1 = User("차다윤", listOf())
val tripPlans = listOf(TripPlan("즐거운 여행", "부산", STATE.PREPARING, Date(10), Date(20), listOf(user1)))
@Singleton
class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getAllTripPlans(): Flow<List<TripPlan>> {
        return flow {
            emit(tripPlans)
        }
    }
}