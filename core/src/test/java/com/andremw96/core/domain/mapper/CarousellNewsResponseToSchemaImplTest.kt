package com.andremw96.core.domain.mapper

import com.andremw96.DummyBuilder.createDummyCarousellNews
import com.andremw96.DummyBuilder.createDummyCarousellNewsItem
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class CarousellNewsResponseToSchemaImplTest {

    private lateinit var carousellNewsResponseToSchemaImpl: CarousellNewsResponseToSchemaImpl

    @Before
    fun setup() {
        carousellNewsResponseToSchemaImpl = CarousellNewsResponseToSchemaImpl()
    }

    @Test
    fun `invoke should map response to schema`() {
        val dummyResponse = listOf(
            createDummyCarousellNewsItem(),
            createDummyCarousellNewsItem(),
        )

        val expectedResult = dummyResponse.map {
            createDummyCarousellNews(it)
        }

        val result = carousellNewsResponseToSchemaImpl(dummyResponse)
        assertEquals(expectedResult, result)
    }
}