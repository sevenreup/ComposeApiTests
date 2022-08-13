package com.skybox.composeapitests.di

import android.content.Context
import com.skybox.composeapitests.network.DataNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    fun providesRetrofit(@ApplicationContext context: Context): DataNetworkService {
        return Retrofit
            .Builder()
            .baseUrl("https://exercise-646d.restdb.io/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor {chain ->
                val request = chain.request().newBuilder().addHeader("x-apikey", "").build()
                chain.proceed(request)
            }.build())
            .build()
            .create(DataNetworkService::class.java)
    }
}