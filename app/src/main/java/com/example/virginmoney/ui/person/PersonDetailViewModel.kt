package com.example.virginmoney.ui.person

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.virginmoney.data.model.people.PeopleModel
import com.example.virginmoney.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val peopleLiveData : MutableLiveData<PeopleModel> by lazy {
        MutableLiveData<PeopleModel>()
    }

    var isLoaded = false

    fun getPeopleData(id:String) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = repository.getPersonById(id)
            peopleLiveData.postValue(result)

            isLoaded = true
        }
    }
}
