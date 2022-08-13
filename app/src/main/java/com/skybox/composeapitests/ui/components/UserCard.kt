package com.skybox.composeapitests.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
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
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "${user.name} ${user.surname}")
            Text(text =  "${user.age}")
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
        UserCard(user = user)
    }
}