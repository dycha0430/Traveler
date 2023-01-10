package com.example.traveler.usecase

import com.example.traveler.model.TripPlan
import kotlinx.coroutines.flow.Flow

interface UseCase<T, A> {
    operator fun invoke(args: A): Flow<T>
}

interface GetAllTripPlansUseCase : UseCase<List<TripPlan>, Unit>