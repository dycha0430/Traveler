package com.example.traveler.di.module

import com.example.traveler.data.repository.HomeRepositoryImpl
import com.example.traveler.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindGetHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository
}