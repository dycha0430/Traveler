package com.example.domain.usecase.impl

import com.example.domain.HomeRepository
import com.example.domain.model.Schedule
import com.example.domain.usecase.CreateScheduleUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateScheduleUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
) : CreateScheduleUseCase {
    override fun invoke(args: Schedule): Flow<Unit> {
        return homeRepository.createSchedule(args)
    }
}