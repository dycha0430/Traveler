package com.example.traveler.data.repository

import com.example.traveler.domain.repository.HomeRepository
import com.example.traveler.model.Destination
import com.example.traveler.model.STATE
import com.example.traveler.model.TripPlan
import com.example.traveler.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

val user1 = User("차다윤", listOf())
val busan = Destination("부산", "")
val tripPlans = listOf(TripPlan(0, "즐거운", busan, STATE.PREPARING, Date(), Date(), listOf(user1)))
@Singleton
class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getAllTripPlans(): Flow<List<TripPlan>> {
        return flow {
            emit(tripPlans)
        }
    }
}