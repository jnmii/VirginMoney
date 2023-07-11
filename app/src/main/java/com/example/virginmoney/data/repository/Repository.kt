package com.example.virginmoney.data.repository

import com.example.virginmoney.data.model.people.PeopleModel
import com.example.virginmoney.data.model.rooms.RoomModel
import retrofit2.http.Path

interface Repository {

    suspend fun  getPersonById(@Path("id") id:String): PeopleModel

    suspend fun getAllPeople() : List<PeopleModel>

    suspend fun getSingleRoom(@Path("id")id:String): RoomModel

    suspend fun getAllRooms():List<RoomModel>


}
