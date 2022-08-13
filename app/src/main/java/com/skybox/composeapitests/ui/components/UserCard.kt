package com.skybox.composeapitests.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skybox.composeapitests.data.model.User
import com.skybox.composeapitests.ui.theme.ComposeApiTestsTheme

@Composable
fun UserCard(user: User) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()) {
            Text(
                text = "${user.name} ${user.surname}",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "${user.age}")
            Text(text = user.city)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserCardPreview() {
    val user = User(
        uuid = "qwerty",
        name = "John",
        surname = "Doe",
        age = 90,
        city = "Dar",
        id = 12,
        parentId = ""
    )
    ComposeApiTestsTheme {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(20) {
                UserCard(user = user)
            }
        }

    }
}