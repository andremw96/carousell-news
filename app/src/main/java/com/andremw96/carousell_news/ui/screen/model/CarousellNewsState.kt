package com.andremw96.carousell_news.ui.screen.model

data class CarousellNewsState(
    val bannerUrl: String,
    val description: String,
    val id: String,
    val rank: Int,
    val timeCreated: Int,
    val timeCreatedString: String,
    val title: String
)