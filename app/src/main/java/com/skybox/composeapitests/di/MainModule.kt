package com.skybox.composeapitests.di

import com.skybox.composeapitests.network.DataNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    fun providesRetrofit(): DataNetworkService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return Retrofit
            .Builder()
            .baseUrl("https://exercise-646d.restdb.io/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor {chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-apikey", "")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }.addInterceptor(logging).build())
            .build()
            .create(DataNetworkService::class.java)
    }
}