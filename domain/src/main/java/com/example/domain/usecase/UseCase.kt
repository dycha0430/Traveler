package com.example.domain.usecase

import com.example.domain.model.Schedule
import com.example.domain.model.TripPlan
import kotlinx.coroutines.flow.Flow

interface UseCase<T, A> {
    operator fun invoke(args: A): Flow<T>
}

interface GetAllTripPlansUseCase : UseCase<List<TripPlan>, Unit>
interface CreateScheduleUseCase : UseCase<Unit, Schedule>