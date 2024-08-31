package com.andremw96.core.domain.mapper

import com.andremw96.core.data.remote.response.CarousellNewsItem
import com.andremw96.core.domain.schema.CarousellNews

interface CarousellNewsResponseToSchema {
    operator fun invoke(carousellNewsResponse: List<CarousellNewsItem>): List<CarousellNews>
}