package com.andremw96.core.di

import com.andremw96.core.data.remote.remotedatasource.CarousellNewsRemoteDataSource
import com.andremw96.core.domain.mapper.CarousellNewsResponseToSchema
import com.andremw96.core.domain.repo.CarousellNewsRepository
import com.andremw96.core.domain.repo.CarousellNewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCarousellRepository(
        carousellNewsRemoteDataSource: CarousellNewsRemoteDataSource,
        carousellNewsResponseToSchema: CarousellNewsResponseToSchema,
    ): CarousellNewsRepository = CarousellNewsRepositoryImpl(
        carousellNewsRemoteDataSource = carousellNewsRemoteDataSource,
        carousellNewsResponseToSchema = carousellNewsResponseToSchema,
    )
}
