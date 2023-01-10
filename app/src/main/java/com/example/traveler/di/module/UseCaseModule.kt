package com.example.traveler.di.module

import com.example.traveler.usecase.GetAllTripPlansUseCase
import com.example.traveler.usecase.impl.GetAllTripPlansUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetAllTripPlansUseCase(getAllTripPlansUseCaseImpl: GetAllTripPlansUseCaseImpl): GetAllTripPlansUseCase
}