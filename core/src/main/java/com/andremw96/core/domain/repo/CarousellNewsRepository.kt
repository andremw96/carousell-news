package com.andremw96.core.domain.repo

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.schema.CarousellNews
import kotlinx.coroutines.flow.Flow

interface CarousellNewsRepository {
    fun getCarousellNews(): Flow<Resource<List<CarousellNews>>>
}
