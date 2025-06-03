package com.toonandtools.main.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(
    @PrimaryKey val id: Int,
    val username: String,
    val name: String,
    val description: String?,
    val htmlUrl: String
)
