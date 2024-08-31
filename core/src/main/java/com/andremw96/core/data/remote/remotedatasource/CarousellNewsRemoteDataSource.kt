package com.andremw96.core.data.remote.remotedatasource

import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.response.CarousellNewsItem
import kotlinx.coroutines.flow.Flow

interface CarousellNewsRemoteDataSource {
    fun getCarousellNews(): Flow<ApiResponse<List<CarousellNewsItem>>>
}
