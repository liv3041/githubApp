package com.toonandtools.main.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RepoScreen(viewModel: RepoViewModel) {
    var username by remember { mutableStateOf("") }  // <-- dynamic username

    val repos = viewModel.repoList
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text("GitHub Username") }
        )

        Button(onClick = { viewModel.fetchRepos(username) }) {
            Text("Fetch Repos")
        }

        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text(error, color = Color.Red)
        } else {
            LazyColumn {
                items(repos) { repo ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(repo.name, fontWeight = FontWeight.Bold)
                            Text(repo.description ?: "No description")
                            Text(repo.htmlUrl, color = Color.Blue)
                        }
                    }
                }
            }
        }
    }
}
