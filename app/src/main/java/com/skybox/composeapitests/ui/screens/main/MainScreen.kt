package com.skybox.composeapitests.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skybox.composeapitests.R
import com.skybox.composeapitests.data.model.User
import com.skybox.composeapitests.ui.components.UserCard
import com.skybox.composeapitests.utils.Response

@ExperimentalMaterial3Api
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val response by viewModel.userListResponse.observeAsState(Response.Idle())

    Scaffold(topBar = {
        MediumTopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        })
    }) { padding ->
        Column(Modifier.padding(padding)) {
            SearchBar(viewModel = viewModel)
            Box(Modifier.fillMaxWidth()) {
                Button(onClick = {
                    viewModel.fetchAll()
                }) {
                    Text(text = "Fetch Data")
                }
            }
            UserListContainer(response = response)
        }
    }
}

@Composable
fun UserListContainer(response: Response<List<User>?>) {
    when (response) {
        is Response.Failure -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Default.Warning, contentDescription = "Error")
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = response.message ?: "")
            }
        }
        is Response.Success -> {
            val data = response.data!!
            LazyColumn() {
                items(data) { user ->
                    UserCard(user)
                }
            }
        }
        is Response.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text(text = "Loading")
            }
        }
        else -> {
            Text(text = "Search City or fetch all data")
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun SearchBar(viewModel: MainViewModel) {
    val searchValue by viewModel.filterValue.observeAsState("")

    TextField(value = searchValue, onValueChange = { value ->
        viewModel.searchChange(value)
    })
}