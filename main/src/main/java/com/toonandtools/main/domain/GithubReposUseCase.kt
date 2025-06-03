package com.toonandtools.main.domain

import com.toonandtools.main.data.GitHubRepositoryImpl

class GetReposUseCase(private val repository: GitHubRepositoryImpl) {
    suspend operator fun invoke(username: String): List<Repo> {
        return repository.getRepos(username)
    }
}
