package com.andremw96.carousell_news.mapper

import com.andremw96.carousell_news.ui.screen.model.CarousellNewsState
import com.andremw96.core.domain.schema.CarousellNews

interface CarousellNewsSchemaToState {
    operator fun invoke(carousellNews: List<CarousellNews>): List<CarousellNewsState>
}