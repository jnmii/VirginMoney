package com.example.virginmoney.data.remote

import com.example.virginmoney.data.model.people.PeopleModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleCall {
    @GET(ApiDetails.PERSON_END_POINT)
    suspend fun  getPersonById(@Path("id") id:Int):PeopleModel

    @GET(ApiDetails.PEOPLE_END_POINT)
    suspend fun getAllPeople() : List<PeopleModel>
}