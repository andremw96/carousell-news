package com.andremw96.carousell_news.di

import com.andremw96.carousell_news.mapper.CarousellNewsSchemaToState
import com.andremw96.carousell_news.mapper.CarousellNewsSchemaToStateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {
    @Provides
    @Singleton
    fun provideCarousellNewsSchemaToState(): CarousellNewsSchemaToState =
        CarousellNewsSchemaToStateImpl()
}
