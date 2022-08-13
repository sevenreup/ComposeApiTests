package com.skybox.composeapitests.data.repository

import com.skybox.composeapitests.data.model.User
import com.skybox.composeapitests.network.DataNetworkService
import com.skybox.composeapitests.utils.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: DataNetworkService) {
    suspend fun getUsers(city: String?): Response<List<User>?> {

        val res = apiService.getGroupOne(createFilter(city))

        if (res.isSuccessful) {
            return Response.Success(data = res.body())
        }

        return Response.Failure(message = res.message())
    }

    private fun createFilter(city: String?): String? {
        if (city != null) return "{\"CITY\": \"${city}\"}"
        return null
    }
}