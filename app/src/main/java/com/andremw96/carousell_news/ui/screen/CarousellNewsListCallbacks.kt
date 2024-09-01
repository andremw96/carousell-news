package com.andremw96.carousell_news.ui.screen

interface CarousellNewsListCallbacks {
    fun loadCarousellNews()
    fun sortNewsBy(sortBy: SortBy)
}

enum class SortBy {
    Recent,
    Popular,
}
