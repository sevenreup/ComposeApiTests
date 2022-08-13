package com.skybox.composeapitests.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skybox.composeapitests.R
import com.skybox.composeapitests.data.model.User
import com.skybox.composeapitests.ui.components.ListStateView
import com.skybox.composeapitests.ui.components.UserCard
import com.skybox.composeapitests.utils.Response

@ExperimentalMaterial3Api
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val response by viewModel.userListResponse.observeAsState(Response.Idle())

    Scaffold(topBar = {
        TopAppBarWithFilter(viewModel)
    }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            UserListContainer(response = response) {
                viewModel.refresh()
            }
        }
    }
}

@Composable
fun UserListContainer(response: Response<List<User>?>, refresh: () -> Unit) {
    val swipeRefreshState = rememberSwipeRefreshState(response !is Response.Loading)

    SwipeRefresh(state = swipeRefreshState, onRefresh = refresh) {
        when (response) {
            is Response.Failure -> {
                swipeRefreshState.isRefreshing = false
                ListStateView(response.message ?: "") {
                    Icon(Icons.Default.Warning, contentDescription = "Error")
                }
            }
            is Response.Success -> {
                swipeRefreshState.isRefreshing = false
                val data = response.data!!

                if (data.isEmpty())
                    ListStateView("The list is empty") {
                        Icon(Icons.Default.Warning, contentDescription = "")
                    }
                else
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(data) { user ->
                            UserCard(user)
                        }
                    }
            }
            is Response.Loading -> {
                swipeRefreshState.isRefreshing = false
                ListStateView("Loading") { CircularProgressIndicator() }
            }
            else -> {
                swipeRefreshState.isRefreshing = false
                ListStateView("Search City or fetch all data") { }
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun TopAppBarWithFilter(viewModel: MainViewModel) {
    val isFilterMode by viewModel.isFilterMode.observeAsState(false)

    Column(Modifier.fillMaxWidth()) {
        SmallTopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            IconButton(onClick = {
                viewModel.toggleFilterMode()
            }) {
                Icon(
                    painter = painterResource(id = if (isFilterMode) R.drawable.ic_clear else R.drawable.ic_filter_list),
                    contentDescription = ""
                )
            }
        })
        AnimatedVisibility(visible = isFilterMode) {
            SearchBar(viewModel = viewModel)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun SearchBar(viewModel: MainViewModel) {
    val searchValue by viewModel.filterValue.observeAsState("")
    val showClearButton by remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp)) {
        Column(Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = searchValue, onValueChange = { value ->
                        viewModel.searchChange(value)
                    }, placeholder = {
                        Text(text = "Filter By City")
                    }, modifier = Modifier
                        .padding(16.dp),
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = showClearButton,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            IconButton(onClick = { viewModel.clearSearch() }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Clear"
                                )
                            }

                        }
                    }
                )
                IconButton(
                    onClick = { viewModel.searchData() },
                    enabled = searchValue.isNotEmpty()
                ) {
                    Icon(Icons.Default.Search, contentDescription = "")
                }
            }
            Divider()
            Button(onClick = { viewModel.clearFilter() }) {
                Text(text = "Clear Filter")
            }
        }

    }

}