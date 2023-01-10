package com.example.traveler.usecase.impl

import com.example.traveler.domain.repository.HomeRepository
import com.example.traveler.model.TripPlan
import com.example.traveler.usecase.GetAllTripPlansUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTripPlansUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
) : GetAllTripPlansUseCase {
    override operator fun invoke(args: Unit): Flow<List<TripPlan>> {
        return homeRepository.getAllTripPlans()
    }
}