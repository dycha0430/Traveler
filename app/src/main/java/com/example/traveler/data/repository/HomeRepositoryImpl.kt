package com.example.traveler.data.repository

import com.example.traveler.domain.repository.HomeRepository
import com.example.traveler.model.*
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

val user1 = User("차다윤", listOf())
val busan = Destination("부산", "")
val place1 = Place("카페", "부산광역시 남포동")
val schedule1 = Schedule(0, 10, "맛있는 카페", TimeFormat(), TimeFormat(), place1)
val dayPlan1 = DayPlan(0, listOf(schedule1))
val tripPlan1 = TripPlan(0, "즐거운", busan, STATE.PREPARING, Date(), Date(), listOf(user1), listOf(dayPlan1))
val tripPlans = listOf(tripPlan1)
@Singleton
class HomeRepositoryImpl @Inject constructor() : HomeRepository {
    override fun getAllTripPlans(): Flow<List<TripPlan>> {
        return flow {
            emit(tripPlans)
        }
    }
}