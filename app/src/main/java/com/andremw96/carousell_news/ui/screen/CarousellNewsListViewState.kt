package com.andremw96.carousell_news.ui.screen

import com.andremw96.carousell_news.ui.screen.model.CarousellNewsState

data class CarousellNewsListViewState(
    val carousellNews: List<CarousellNewsState>,
    val isLoading: Boolean,
    val errorMessage: String?,
    val sortedBy: SortBy,
) {
    companion object {
        fun initialState(): CarousellNewsListViewState =
            CarousellNewsListViewState(
                carousellNews = emptyList(),
                isLoading = false,
                errorMessage = null,
                sortedBy = SortBy.Recent,
            )
    }
}
