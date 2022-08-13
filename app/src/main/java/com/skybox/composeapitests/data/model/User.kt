package com.skybox.composeapitests.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id") val uuid: String,
    @SerializedName("NAME") val name: String,
    @SerializedName("SURNAME") val surname: String,
    @SerializedName("AGE") val age: Int, @SerializedName("CITY")
    val city: String,
    @SerializedName("ID") val id: Int,
    @SerializedName("PARENTID")
    val parentId: Int
)