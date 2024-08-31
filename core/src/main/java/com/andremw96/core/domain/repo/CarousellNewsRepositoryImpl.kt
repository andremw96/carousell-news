package com.andremw96.core.domain.repo

import com.andremw96.core.data.Resource
import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.remotedatasource.CarousellNewsRemoteDataSource
import com.andremw96.core.domain.mapper.CarousellNewsResponseToSchema
import com.andremw96.core.domain.schema.CarousellNews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CarousellNewsRepositoryImpl @Inject constructor(
    private val carousellNewsRemoteDataSource: CarousellNewsRemoteDataSource,
    private val carousellNewsResponseToSchema: CarousellNewsResponseToSchema,
) : CarousellNewsRepository {
    override fun getCarousellNews(): Flow<Resource<List<CarousellNews>>> {
        return flow {
            emit(Resource.Loading())
            carousellNewsRemoteDataSource.getCarousellNews().collect {
                when (it) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(carousellNewsResponseToSchema(it.data)))
                    }

                    is ApiResponse.Empty -> {
                        emit(Resource.Success(emptyList()))
                    }

                    is ApiResponse.Error -> {
                        emit(Resource.Error(it.errorMessage))
                    }
                }
            }
        }
    }
}
