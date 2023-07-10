package com.example.virginmoney.data.repository


import com.example.virginmoney.data.remote.PeopleCall
import com.example.virginmoney.data.remote.RoomCall

import javax.inject.Inject

class RepoImpl @Inject constructor(
    val roomCall: RoomCall,
    val peopleCall: PeopleCall
):Repository {
    override suspend fun  getPersonById(id:Int)= peopleCall.getPersonById(id)

    override suspend fun getAllPeople()= peopleCall.getAllPeople()

    override suspend fun getSingleRoom(id:Int)= roomCall.getSingleRoom(id)

    override suspend fun getAllRooms()= roomCall.getAllRooms()
}