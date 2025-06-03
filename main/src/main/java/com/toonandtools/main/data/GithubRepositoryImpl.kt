package com.toonandtools.main.data

import android.util.Log
import com.toonandtools.main.db.RepoDao
import com.toonandtools.main.db.RepoEntity
import com.toonandtools.main.domain.GitHubRepository
import com.toonandtools.main.domain.Repo

//
//import com.toonandtools.main.domain.GitHubRepository
//import com.toonandtools.main.domain.Repo
//
//class GitHubRepositoryImpl(private val api: GitHubApi) : GitHubRepository {
//    override suspend fun getRepos(username: String): List<Repo> {
//        return api.getUserRepos(username).map { it.toRepo() }
//    }
//}
class GitHubRepositoryImpl(
    private val api: GitHubApi,
    private val dao: RepoDao
) : GitHubRepository {

    override suspend fun getRepos(username: String): List<Repo> {
        return try {
            val networkRepos = api.getUserRepos(username).map { it.toRepo() }
            Log.d("API", "Fetched from network: ${networkRepos.size} repos")

            val entities = networkRepos.map {
                RepoEntity(
                    id = it.id,
                    username = username,
                    name = it.name,
                    description = it.description,
                    htmlUrl = it.htmlUrl
                )
            }
            Log.d("RoomCache", "Caching entities: $entities")

            dao.insertRepos(entities)
            networkRepos
        } catch (e: Exception) {
            // Return cached if network fails
            val cachedRepos = dao.getReposByUsername(username)

            Log.d("RoomCache", "Loaded ${cachedRepos.size} repos from cache for user $username")
            Log.d("RoomCache", "Loaded from cache: ${cachedRepos.size} repos")


            return  cachedRepos.map {
                Repo(
                    id = it.id,
                    name = it.name,
                    description = it.description!!,
                    htmlUrl = it.htmlUrl
                )
            }
        }
    }
}
