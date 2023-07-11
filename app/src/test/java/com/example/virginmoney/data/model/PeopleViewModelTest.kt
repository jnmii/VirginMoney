package com.example.virginmoney.data.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.virginmoney.data.model.people.PeopleModel
import com.example.virginmoney.data.repository.Repository
import com.example.virginmoney.ui.people.PeopleViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PeopleViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val standardDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: PeopleViewModel


    @Mock
    lateinit var repository: Repository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = PeopleViewModel(repository)
        Dispatchers.setMain(standardDispatcher)
    }

    @Test
    fun `verify getPeopleByIdData function retrieves contact data`() = runTest {
        val peopleData = PeopleModel()
        peopleData.add(getObject())


        `when`(repository.getAllPeople()).thenReturn(peopleData)

        viewModelpeopleData()
        standardDispatcher.scheduler.advanceUntilIdle()

        assertEquals(peopleData, viewModel.peopleLiveData.value)
    }



    fun getObject() = PeopleModel(
        avatar = "https://randomuser.me/api/portraits/women/21.jpg",
        createdAt = "2022-01-24T17:02:23.729Z",

        email = "Crystel.Nicolas61@hotmail.com",
        favouriteColor = "pink",
        firstName = "Maggie",

        id = "1",
        jobtitle = "Future Functionality Strategist",
        lastName = "Brekke",

    )


}