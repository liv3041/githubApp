package com.toonandtools.main.data

import com.toonandtools.main.domain.GitHubRepository
import com.toonandtools.main.domain.Repo

class GitHubRepositoryImpl(private val api: GitHubApi) : GitHubRepository {
    override suspend fun getRepos(username: String): List<Repo> {
        return api.getUserRepos(username).map { it.toRepo() }
    }
}
