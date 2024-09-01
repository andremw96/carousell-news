package com.andremw96.core.domain.usecase

import com.andremw96.core.data.Resource
import com.andremw96.core.domain.repo.CarousellNewsRepository
import com.andremw96.core.domain.schema.CarousellNews
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetCarousellNewsImplTest {

    @Mock
    private lateinit var mockedCarousellNewsRepository: CarousellNewsRepository

    private lateinit var getCarousellNewsImpl: GetCarousellNewsImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getCarousellNewsImpl = GetCarousellNewsImpl(
            mockedCarousellNewsRepository,
        )
    }

    @Test
    fun `invoke should call repository`() {
        runTest {
            val dummyDataRepo: Flow<Resource<List<CarousellNews>>> = mock()

            whenever(mockedCarousellNewsRepository.getCarousellNews()).thenReturn(
                dummyDataRepo
            )

            val result = getCarousellNewsImpl()

            assertEquals(dummyDataRepo, result)

            verify(mockedCarousellNewsRepository).getCarousellNews()
        }
    }
}