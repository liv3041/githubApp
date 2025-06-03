package com.toonandtools.main.domain

interface GitHubRepository {
    suspend fun getRepos(username: String): List<Repo>
}
