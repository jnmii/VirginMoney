package com.example.virginmoney.data.model.people

import com.google.gson.annotations.SerializedName

data class PeopleModel(
    @SerializedName("createdAt")
    val createdAt: String? = "",
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("avatar")
    val avatar: String? = "",
    @SerializedName("lastName")
    val lastName: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("jobtitle")
    val jobtitle: String? = "",
    @SerializedName("favouriteColor")
    val favouriteColor: String? = "",
    @SerializedName("id")
    val id: String? = ""
)
