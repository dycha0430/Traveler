package com.example.domain.di.module

import com.example.domain.usecase.CreateScheduleUseCase
import com.example.domain.usecase.CreateTripPlanUseCase
import com.example.domain.usecase.GetAllTripPlansUseCase
import com.example.domain.usecase.impl.CreateScheduleUseCaseImpl
import com.example.domain.usecase.impl.CreateTripPlanUseCaseImpl
import com.example.domain.usecase.impl.GetAllTripPlansUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetAllTripPlansUseCase(getAllTripPlansUseCaseImpl: GetAllTripPlansUseCaseImpl): GetAllTripPlansUseCase
    @Binds
    fun bindCreateTripPlanUseCase(createTripPlansUseCaseImpl: CreateTripPlanUseCaseImpl): CreateTripPlanUseCase
    @Binds
    fun bindCreateScheduleUseCase(createScheduleUseCaseImpl: CreateScheduleUseCaseImpl): CreateScheduleUseCase
}