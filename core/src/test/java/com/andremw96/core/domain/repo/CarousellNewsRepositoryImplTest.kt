package com.andremw96.core.domain.repo

import com.andremw96.DummyBuilder.createDummyCarousellNews
import com.andremw96.DummyBuilder.createDummyCarousellNewsItem
import com.andremw96.core.MainDispatcherRule
import com.andremw96.core.data.Resource
import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.remotedatasource.CarousellNewsRemoteDataSource
import com.andremw96.core.data.remote.response.CarousellNewsItem
import com.andremw96.core.domain.mapper.CarousellNewsResponseToSchema
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class CarousellNewsRepositoryImplTest {

    private val coroutineDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(coroutineDispatcher)

    @Mock
    private lateinit var mockedCarousellNewsRemoteDataSource: CarousellNewsRemoteDataSource

    @Mock
    private lateinit var mockedCarousellNewsResponseToSchema: CarousellNewsResponseToSchema

    private lateinit var carousellNewsRepositoryImpl: CarousellNewsRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        carousellNewsRepositoryImpl = CarousellNewsRepositoryImpl(
            carousellNewsRemoteDataSource = mockedCarousellNewsRemoteDataSource,
            carousellNewsResponseToSchema = mockedCarousellNewsResponseToSchema
        )
    }

    @Test
    fun `getCarousellNews should call data source data and return flow success data`() {
        val dummyCarousellNewsItem = listOf(
            createDummyCarousellNewsItem(),
            createDummyCarousellNewsItem(),
        )

        val dummyCarousellNews = dummyCarousellNewsItem.map {
            createDummyCarousellNews(it)
        }


        val dummyDataSource = flow<ApiResponse<List<CarousellNewsItem>>> {
            emit(ApiResponse.Success(dummyCarousellNewsItem))
        }

        runTest {
            whenever(mockedCarousellNewsRemoteDataSource.getCarousellNews()).thenReturn(
                dummyDataSource
            )

            whenever(mockedCarousellNewsResponseToSchema(dummyCarousellNewsItem)).thenReturn(
                dummyCarousellNews
            )

            val result = carousellNewsRepositoryImpl.getCarousellNews()
            result.collectIndexed { index, value ->
                when (index) {
                    0 -> {
                        assert(value is Resource.Loading)
                    }

                    1 -> {
                        assert(value is Resource.Success)
                        assertEquals(dummyCarousellNews, (value as Resource.Success).data)
                    }
                }
            }
        }
    }

    @Test
    fun `getCarousellNews should call data source empty and return flow success empty`() {
        val dummyDataSource = flow<ApiResponse<List<CarousellNewsItem>>> {
            emit(ApiResponse.Success(emptyList()))
        }

        runTest {
            whenever(mockedCarousellNewsRemoteDataSource.getCarousellNews()).thenReturn(
                dummyDataSource
            )

            val result = carousellNewsRepositoryImpl.getCarousellNews()
            result.collectIndexed { index, value ->
                when (index) {
                    0 -> {
                        assert(value is Resource.Loading)
                    }

                    1 -> {
                        assert(value is Resource.Success)
                        assert((value as Resource.Success).data!!.isEmpty())
                    }
                }
            }
        }
    }

    @Test
    fun `getCarousellNews should call data source error and return flow error`() {
        val dummyException = "error"
        val dummyDataSource = flow<ApiResponse<List<CarousellNewsItem>>> {
            emit(ApiResponse.Error(dummyException))
        }

        runTest {
            whenever(mockedCarousellNewsRemoteDataSource.getCarousellNews()).thenReturn(
                dummyDataSource
            )

            val result = carousellNewsRepositoryImpl.getCarousellNews()
            result.collectIndexed { index, value ->
                when (index) {
                    0 -> {
                        assert(value is Resource.Loading)
                    }

                    1 -> {
                        assert(value is Resource.Error)
                        assertEquals(dummyException, (value as Resource.Error).message)
                    }
                }
            }
        }
    }
}