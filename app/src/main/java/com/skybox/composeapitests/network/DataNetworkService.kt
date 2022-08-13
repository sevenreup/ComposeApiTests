package com.skybox.composeapitests.network

import com.skybox.composeapitests.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataNetworkService {
    @GET("group-1")
    suspend fun getGroupOne(@Query("q") filter: String?): Response<List<User>>
}