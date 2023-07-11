package com.example.virginmoney.data.model.repository



import com.example.virginmoney.TestUtil
import com.example.virginmoney.data.model.people.PeopleModel
import com.example.virginmoney.data.model.rooms.RoomModel
import com.example.virginmoney.data.remote.PeopleCall
import com.example.virginmoney.data.remote.RoomCall
import com.example.virginmoney.data.repository.Repository
import org.junit.Assert.*


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RepositoryImplTest {

    private lateinit var repository: Repository

    @Mock
    lateinit var peopleCall: PeopleCall
    lateinit var roomCall: RoomCall

    private val standardDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = RepositoryImpl(peopleCall)
    }

    @Test
    fun `verify getAllPeople() function returns expected people model`() = runTest{
        // Given
        val expectedPeopleModel = PeopleModel()
        expectedPeopleModel.add(TestUtil.getDummyPeopleModel())
        `when`(peopleCall.getAllPeople()).thenReturn(expectedPeopleModel)

        // When
        val actualPeopleModel = repository.getAllPeople()
        standardDispatcher.scheduler.advanceUntilIdle()


        // Then
        assertEquals(expectedPeopleModel, actualPeopleModel)
    }

    @Test
    fun `verify getAllRooms() function returns expected room model`() = runBlocking {
        // Given
        // Given
        val expectedRoomModel = RoomModel()
        expectedRoomModel.add(TestUtil.getDummyRoomModel())
        `when`(roomCall.getAllRooms()).thenReturn(expectedRoomModel)

        // When
        val actualRoomModel = repository.getAllRooms()
        standardDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(expectedRoomModel, actualRoomModel)
    }

    @Test
    fun `verify getAllPeople() function returns non-null results`() = runBlocking {
        // Given
        val expectedPeopleModel = PeopleModel()
        expectedPeopleModel.add(TestUtil.getDummyPeopleModel())
        `when`(peopleCall.getAllPeople()).thenReturn(expectedPeopleModel)

        // When
        val actualPeopleModel = repository.getAllPeople()
        standardDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertNotNull(actualPeopleModel)
    }

    @Test
    fun `verify getRooms() function returns non-null results`() = runBlocking {
        // Given
        val expectedRoomModel = RoomModel()
        expectedRoomModel.add(TestUtil.getDummyRoomModel())
        `when`(roomCall.getAllRooms()).thenReturn(expectedRoomModel)

        // When
        val actualRoomModel = repository.getAllRooms()
        standardDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertNotNull(actualRoomModel)
    }
}


