package com.andremw96.core.domain.mapper

import com.andremw96.core.data.remote.response.CarousellNewsItem
import com.andremw96.core.domain.schema.CarousellNews

class CarousellNewsResponseToSchemaImpl : CarousellNewsResponseToSchema {
    override fun invoke(carousellNewsResponse: List<CarousellNewsItem>): List<CarousellNews> {
        return carousellNewsResponse.map {
            CarousellNews(
                bannerUrl = it.bannerUrl,
                description = it.description,
                id = it.id,
                rank = it.rank,
                timeCreated = it.timeCreated,
                title = it.title
            )
        }
    }
}