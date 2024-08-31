package com.andremw96.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class CarousellNewsItem(
    @SerializedName("banner_url")
    val bannerUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("time_created")
    val timeCreated: Int,
    @SerializedName("title")
    val title: String
)