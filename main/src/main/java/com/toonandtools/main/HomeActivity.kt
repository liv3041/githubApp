package com.toonandtools.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.toonandtools.main.data.GitHubApi
import com.toonandtools.main.data.GitHubRepositoryImpl
import com.toonandtools.main.db.AppDatabase
import com.toonandtools.main.domain.GetReposUseCase
import com.toonandtools.main.presentation.RepoScreen
import com.toonandtools.main.presentation.RepoViewModel
import com.toonandtools.main.presentation.RepoViewModelFactory
import com.toonandtools.main.ui.theme.GithubAppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeActivity : ComponentActivity() {

    private lateinit var viewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Retrofit, Api, Repo, UseCase
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(GitHubApi::class.java)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "github-db"
        ).build()
        val dao = db.repoDao()

        val repository = GitHubRepositoryImpl(api,dao)
        val useCase = GetReposUseCase(repository)

        // Create ViewModel using factory
        val factory = RepoViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, factory).get(RepoViewModel::class.java)

        setContent {
            GithubAppTheme {
                RepoScreen(viewModel)
            }
        }
    }
}
