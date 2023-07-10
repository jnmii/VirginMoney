package com.example.virginmoney.data.model.rooms
import com.google.gson.annotations.SerializedName

data class RoomModel(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("isOccupied")
    val isOccupied: Boolean,
    @SerializedName("createdAt")
    val maxOccupancy: Int,
    @SerializedName("createdAt")
    val id: String
)