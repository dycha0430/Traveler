package com.example.data.di.module

import com.example.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
    companion object {
        const val BASE_URL = "http://localhost:4000/"
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService(BASE_URL)
    }
}