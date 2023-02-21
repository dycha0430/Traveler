package com.example.domain.di.module

import com.example.domain.usecase.GetAllTripPlansUseCase
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
}