package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.repo.CarousellNewsRepository
import com.andremw96.core.domain.schema.CarousellNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarousellNewsImpl @Inject constructor(
    private val carousellNewsRepository: CarousellNewsRepository,
) : GetCarousellNews {
    override fun invoke(): Flow<Resource<List<CarousellNews>>> {
        return carousellNewsRepository.getCarousellNews()
    }
}