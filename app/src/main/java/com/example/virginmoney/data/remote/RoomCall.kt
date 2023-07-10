package com.example.virginmoney.data.remote

import com.example.virginmoney.data.model.rooms.RoomModel
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomCall {
    @GET(ApiDetails.ALL_ROOMS_END_POINT)
    suspend fun getAllRooms():List<RoomModel>

    @GET(ApiDetails.SINGLE_ROOM_END_POINT)
    suspend fun getSingleRoom(@Path("id")id:Int): RoomModel
}