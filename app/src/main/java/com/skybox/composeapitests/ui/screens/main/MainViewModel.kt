package com.skybox.composeapitests.ui.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skybox.composeapitests.data.model.User
import com.skybox.composeapitests.data.repository.MainRepository
import com.skybox.composeapitests.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val filterValue = MutableLiveData("")
    val userListResponse = MutableLiveData<Response<List<User>?>>(Response.Idle())

    fun searchChange(value: String) {
        filterValue.value = value

        if (value.length > 3) {
            getData(value)
        }
    }

    fun fetchAll() {
        getData(null)
    }

    private fun getData(city: String?) {
        userListResponse.value = Response.Loading()
        viewModelScope.launch {
            val res = repository.getUsers(city)
            userListResponse.value = res
        }
    }
}