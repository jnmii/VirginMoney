package com.example.virginmoney

import com.example.virginmoney.data.model.people.PeopleModel
import com.example.virginmoney.data.model.rooms.RoomModel

class TestUtil {
    companion object {
        fun getDummyPeopleModel() = PeopleModel(
            avatar = "https://randomuser.me/api/portraits/women/21.jpg",
            createdAt = "2022-01-24T17:02:23.729Z",

            email = "Crystel.Nicolas61@hotmail.com",
            favouriteColor = "pink",
            firstName = "Maggie",

            id = "1",
            jobtitle = "Future Functionality Strategist",
            lastName = "Brekke",

        )

        fun getDummyRoomModel() = RoomModel(
            createdAt = "2022-01-24T20:52:50.765Z",
            id = "1",
            isOccupied = false,
            maxOccupancy = 53539
        )


    }
}