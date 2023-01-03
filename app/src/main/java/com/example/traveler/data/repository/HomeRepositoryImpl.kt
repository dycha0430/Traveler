package com.example.traveler.data.repository

import com.example.traveler.domain.repository.HomeRepository
import com.example.traveler.model.STATE
import com.example.traveler.model.TripPlan
import com.example.traveler.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

val user1 = User("차다윤", listOf())
val tripPlans = listOf(TripPlan("즐거운 여행", "부산", STATE.PREPARING, Date(10), Date(20), listOf(user1)))
class HomeRepositoryImpl : HomeRepository {
    override fun getAllTripPlans(): Flow<List<TripPlan>> {
        return flow {
            emit(tripPlans)
        }
    }
}