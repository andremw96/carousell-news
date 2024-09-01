package com.andremw96.carousell_news.ui.screen

import com.andremw96.core.domain.schema.CarousellNews

data class CarousellNewsListViewState(
    val carousellNews: List<CarousellNews>,
    val isLoading: Boolean,
    val errorMessage: String?,
) {
    companion object {
        fun initialState(): CarousellNewsListViewState =
            CarousellNewsListViewState(
                carousellNews = emptyList(),
                isLoading = false,
                errorMessage = null
            )
    }
}
