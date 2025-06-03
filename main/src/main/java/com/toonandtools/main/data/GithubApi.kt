package com.toonandtools.main.data

import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): List<RepoDto>
}
