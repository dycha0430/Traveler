package com.example.data.di.module

import com.example.data.HomeRepositoryImpl
import com.example.domain.HomeRepository
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