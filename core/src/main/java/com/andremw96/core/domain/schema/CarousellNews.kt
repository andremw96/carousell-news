package com.andremw96.core.domain.schema


data class CarousellNews(
    val bannerUrl: String,
    val description: String,
    val id: String,
    val rank: Int,
    val timeCreated: Int,
    val title: String
)