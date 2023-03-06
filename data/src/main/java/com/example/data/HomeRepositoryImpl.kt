package com.example.data

import com.example.data.mapper.DtoToTripPlanMapper
import com.example.data.mapper.destinationMapper
import com.example.data.mapper.stateMapper
import com.example.domain.HomeRepository
import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.Period
import javax.inject.Inject
import javax.inject.Singleton

//val user1 = User()
//val tripPlan1 = TripPlan(listOf(user1))
//val tripPlans = listOf(tripPlan1)
@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : HomeRepository {
    override fun getAllTripPlans(): Flow<List<TripPlan>> {
        val flow = apiService.getAllTripPlans().map {
            val list = mutableListOf<TripPlan>()
            it.forEach {tripPlanDto ->
                list.add(
                    DtoToTripPlanMapper(tripPlanDto)
                )
            }
            list
        }
        return flow
    }

    override fun createSchedule(schedule: Schedule): Flow<Unit> {
        // TODO save schedule
        return flow {}
    }

    override fun createTripPlan(
        tripPlan: TripPlan
    ): Flow<Unit> {
        val totalDay = Period.between(tripPlan.startDate.toLocalDate(), tripPlan.endDate.toLocalDate()).days
        return apiService.createTripPlan(tripPlan.title, tripPlan.destination.id, tripPlan.participants[0].id, tripPlan.startDate, totalDay)
    }
}