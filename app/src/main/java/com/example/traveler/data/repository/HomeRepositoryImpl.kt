package com.example.traveler.data.repository

import com.example.traveler.domain.repository.HomeRepository
import com.example.traveler.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

val user1 = User()
val tripPlan1 = TripPlan(listOf(user1))
val tripPlans = listOf(tripPlan1)
@Singleton
class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getAllTripPlans(): Flow<List<TripPlan>> {
        return flow {
            emit(tripPlans)
        }
    }
}