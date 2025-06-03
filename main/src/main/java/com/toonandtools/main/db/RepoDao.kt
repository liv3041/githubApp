package com.toonandtools.main.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepoDao {
    //    @Query("SELECT * FROM repos")
//    suspend fun getAllRepos(): List<RepoEntity>
    @Query("SELECT * FROM repos WHERE username = :username")
    suspend fun getReposByUsername(username: String): List<RepoEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<RepoEntity>)
}
