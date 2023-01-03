package com.example.traveler.usecase.impl

import com.example.traveler.domain.repository.HomeRepository
import com.example.traveler.model.TripPlan
import com.example.traveler.usecase.GetAllTripPlansUseCase
import kotlinx.coroutines.flow.Flow

class GetAllTripPlansUseCaseImpl constructor(
    private val homeRepository: HomeRepository
) : GetAllTripPlansUseCase {
    override operator fun invoke(args: Unit): Flow<List<TripPlan>> {
        return homeRepository.getAllTripPlans()
    }
}