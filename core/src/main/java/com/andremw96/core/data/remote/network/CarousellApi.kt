package com.andremw96.core.data.remote.network

import com.andremw96.core.data.remote.response.CarousellNewsItem
import retrofit2.http.GET

interface CarousellApi {
    @GET("carousell-interview-assets/android/carousell_news.json")
    suspend fun getCarousellNews(): List<CarousellNewsItem>
}
