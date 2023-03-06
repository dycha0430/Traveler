package com.example.domain.usecase.impl

import com.example.domain.HomeRepository
import com.example.domain.model.TripPlan
import com.example.domain.usecase.CreateTripPlanUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateTripPlanUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
) : CreateTripPlanUseCase {
    override fun invoke(args: TripPlan): Flow<Unit> {
        return homeRepository.createTripPlan(args)
    }
}