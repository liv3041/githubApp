package com.toonandtools.main.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toonandtools.main.domain.GetReposUseCase
import com.toonandtools.main.domain.Repo
import kotlinx.coroutines.launch

class RepoViewModel(private val getReposUseCase: GetReposUseCase) : ViewModel() {

    var repoList by mutableStateOf<List<Repo>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun fetchRepos(username: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                repoList = getReposUseCase(username)
            } catch (e: Exception) {
                errorMessage = "Failed: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}

