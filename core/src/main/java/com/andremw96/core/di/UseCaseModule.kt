package com.andremw96.core.di

import com.andremw96.core.domain.repo.CarousellNewsRepository
import com.andremw96.core.domain.usecase.GetCarousellNews
import com.andremw96.core.domain.usecase.GetCarousellNewsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetCarousellNews(
        carousellNewsRepository: CarousellNewsRepository,
    ): GetCarousellNews = GetCarousellNewsImpl(
        carousellNewsRepository = carousellNewsRepository,
    )
}
