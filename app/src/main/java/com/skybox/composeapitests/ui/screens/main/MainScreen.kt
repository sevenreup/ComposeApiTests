package com.skybox.composeapitests.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.skybox.composeapitests.R

@ExperimentalMaterial3Api
@Composable
fun MainScreen() {
    Scaffold(topBar = {
        MediumTopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        })
    }) { padding ->
        Column(Modifier.padding(padding)) {

        }
    }
}