package com.example.domain.usecase.impl

import com.example.domain.HomeRepository
import com.example.domain.model.TripPlan
import com.example.domain.usecase.GetAllTripPlansUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTripPlansUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
) : GetAllTripPlansUseCase {
    override operator fun invoke(args: Unit): Flow<List<TripPlan>> {
        return homeRepository.getAllTripPlans()
    }
}