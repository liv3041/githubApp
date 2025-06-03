package com.toonandtools.main.data

import com.toonandtools.main.domain.Repo

fun RepoDto.toRepo(): Repo {
    return Repo(id, name, description ?: "No description", html_url)
}
