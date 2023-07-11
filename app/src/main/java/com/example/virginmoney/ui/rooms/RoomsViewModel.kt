package com.example.virginmoney.ui.rooms


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.virginmoney.data.repository.Repository
import com.example.virginmoney.data.model.rooms.RoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val roomsLiveData : MutableLiveData<List<RoomModel>> by lazy {
        MutableLiveData<List<RoomModel>>()
    }

    var isLoaded = false

    fun getRoomsData() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = repository.getAllRooms()
            roomsLiveData.postValue(result)

            isLoaded = true

        }
    }
}