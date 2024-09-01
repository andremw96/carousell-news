package com.andremw96.core.data.remote.remotedatasource.impl

import com.andremw96.DummyBuilder.createDummyCarousellNewsItem
import com.andremw96.core.MainDispatcherRule
import com.andremw96.core.data.remote.network.ApiResponse
import com.andremw96.core.data.remote.network.CarousellApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.exceptions.base.MockitoException
import org.mockito.kotlin.whenever

class CarousellNewsRemoteDataSourceImplTest {

    private val coroutineDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(coroutineDispatcher)

    @Mock
    private lateinit var mockedCarousellApi: CarousellApi

    private lateinit var carousellNewsRemoteDataSourceImpl: CarousellNewsRemoteDataSourceImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        carousellNewsRemoteDataSourceImpl = CarousellNewsRemoteDataSourceImpl(
            coroutineDispatcher = coroutineDispatcher,
            carousellApi = mockedCarousellApi,
        )
    }

    @Test
    fun `getCarousellNews should call API and emit success`() {
        val dummyCarousellNewsResponse = listOf(
            createDummyCarousellNewsItem(),
            createDummyCarousellNewsItem(),
        )

        runTest {
            whenever(mockedCarousellApi.getCarousellNews()).thenReturn(
                dummyCarousellNewsResponse
            )

            val result = carousellNewsRemoteDataSourceImpl.getCarousellNews()
            result.collectIndexed { index, value ->
                assert(value is ApiResponse.Success)
                assertEquals(dummyCarousellNewsResponse, (value as ApiResponse.Success).data)
            }
        }
    }

    @Test
    fun `getCarousellNews should call API return empty and emit empty`() {
        runTest {
            whenever(mockedCarousellApi.getCarousellNews()).thenReturn(
                emptyList()
            )

            val result = carousellNewsRemoteDataSourceImpl.getCarousellNews()
            result.collectIndexed { index, value ->
                assert(value is ApiResponse.Empty)
            }
        }
    }

    @Test
    fun `getCarousellNews should call API return error and emit error`() {
        val dummyException = MockitoException("error")

        runTest {
            whenever(mockedCarousellApi.getCarousellNews()).thenThrow(
                dummyException
            )

            val result = carousellNewsRemoteDataSourceImpl.getCarousellNews()
            result.collectIndexed { index, value ->
                assert(value is ApiResponse.Error)
                assertEquals(dummyException.toString(), (value as ApiResponse.Error).errorMessage)
            }
        }
    }
}