package com.andremw96.carousell_news.mapper

import com.andremw96.carousell_news.ui.screen.model.CarousellNewsState
import com.andremw96.core.domain.schema.CarousellNews
import java.util.concurrent.TimeUnit

class CarousellNewsSchemaToStateImpl : CarousellNewsSchemaToState {
    override fun invoke(carousellNews: List<CarousellNews>): List<CarousellNewsState> {
        return carousellNews.map {
            CarousellNewsState(
                bannerUrl = it.bannerUrl,
                description = it.description,
                id = it.id,
                rank = it.rank,
                timeCreated = calculateTimeCreated(it.timeCreated),
                title = it.title
            )
        }
    }

    private fun calculateTimeCreated(timeCreatedInMillis: Int): String {
        val now = System.currentTimeMillis()
        val diff = now - timeCreatedInMillis

        return when {
            diff < TimeUnit.MINUTES.toMillis(1) -> "just now"
            diff < TimeUnit.HOURS.toMillis(1) -> "${TimeUnit.MILLISECONDS.toMinutes(diff)} minutes ago"
            diff < TimeUnit.DAYS.toMillis(1) -> "${TimeUnit.MILLISECONDS.toHours(diff)} hours ago"
            diff < TimeUnit.DAYS.toMillis(7) -> "${TimeUnit.MILLISECONDS.toDays(diff)} days ago"
            diff < TimeUnit.DAYS.toMillis(30) -> "${TimeUnit.MILLISECONDS.toDays(diff) / 7} weeks ago"
            diff < TimeUnit.DAYS.toMillis(365) -> "${TimeUnit.MILLISECONDS.toDays(diff) / 30} months ago"
            else -> "${TimeUnit.MILLISECONDS.toDays(diff) / 365} years ago"
        }
    }
}