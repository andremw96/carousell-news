package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.schema.CarousellNews
import kotlinx.coroutines.flow.Flow

interface GetCarousellNews {
    operator fun invoke(): Flow<Resource<List<CarousellNews>>>
}