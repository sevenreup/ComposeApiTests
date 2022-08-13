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
    val isFilterMode = MutableLiveData(false)
    val userListResponse = MutableLiveData<Response<List<User>?>>(Response.Idle())

    init {
        getData(null)
    }

    fun searchChange(value: String) {
        isFilterMode.value = true
        filterValue.value = value
    }

    fun searchData() {
        filterValue.value?.let { value ->
            getData(value)
        }
    }

    fun toggleFilterMode() {
        isFilterMode.value = !(isFilterMode.value ?: false)
    }

    fun clearSearch() {
        filterValue.value = ""
    }

    fun clearFilter() {
        filterValue.value = ""
        isFilterMode.value = false
        refresh()
    }

    fun refresh() {
        if (isFilterMode.value == true) searchData() else getData(null)
    }

    private fun getData(city: String?) {
        userListResponse.value = Response.Loading()
        viewModelScope.launch {
            val res = repository.getUsers(city)
            userListResponse.value = res
        }
    }
}