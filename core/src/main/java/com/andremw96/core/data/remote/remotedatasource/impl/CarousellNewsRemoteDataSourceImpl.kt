package com.andremw96.core.data.remote.remotedatasource.impl

import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.network.CarousellApi
import com.andremw96.core.data.remote.remotedatasource.CarousellNewsRemoteDataSource
import com.andremw96.core.data.remote.response.CarousellNewsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CarousellNewsRemoteDataSourceImpl @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val carousellApi: CarousellApi,
) : CarousellNewsRemoteDataSource {
    override fun getCarousellNews(): Flow<ApiResponse<List<CarousellNewsItem>>> {
        return flow {
            try {
                val carousellNews = carousellApi.getCarousellNews()

                if (carousellNews.isNotEmpty()) {
                    emit(ApiResponse.Success(carousellNews))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(coroutineDispatcher)
    }
}
