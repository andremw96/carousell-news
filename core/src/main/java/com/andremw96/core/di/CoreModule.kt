package com.andremw96.core.di

import com.andremw96.core.BuildConfig
import com.andremw96.core.data.remote.network.CarousellApi
import com.andremw96.core.data.remote.remotedatasource.CarousellNewsRemoteDataSource
import com.andremw96.core.data.remote.remotedatasource.impl.CarousellNewsRemoteDataSourceImpl
import com.andremw96.core.domain.mapper.CarousellNewsResponseToSchema
import com.andremw96.core.domain.mapper.CarousellNewsResponseToSchemaImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseURL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CarousellApi =
        retrofit.create(CarousellApi::class.java)

    @IoDispatcher
    @Provides
    @Singleton
    fun providesIoDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    fun provideCarousellNewsRemoteDataSource(
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
        carousellApi: CarousellApi,
    ): CarousellNewsRemoteDataSource = CarousellNewsRemoteDataSourceImpl(
        coroutineDispatcher = coroutineDispatcher,
        carousellApi = carousellApi,
    )

    @Provides
    @Singleton
    fun provideCarousellNewsResponseToSchema(): CarousellNewsResponseToSchema =
        CarousellNewsResponseToSchemaImpl()
}
