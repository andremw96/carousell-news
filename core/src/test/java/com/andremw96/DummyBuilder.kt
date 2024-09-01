package com.andremw96

import com.andremw96.core.data.remote.response.CarousellNewsItem
import com.andremw96.core.domain.schema.CarousellNews
import net.bytebuddy.utility.RandomString
import kotlin.random.Random

object DummyBuilder {
    fun createDummyCarousellNewsItem(): CarousellNewsItem {
        return CarousellNewsItem(
            RandomString.make(2),
            RandomString.make(2),
            RandomString.make(2),
            Random.nextInt(),
            Random.nextInt(),
            RandomString.make(2),
        )
    }

    fun createDummyCarousellNews(response: CarousellNewsItem): CarousellNews {
        return CarousellNews(
            response.bannerUrl,
            response.description,
            response.id,
            response.rank,
            response.timeCreated,
            response.title,
        )
    }
}