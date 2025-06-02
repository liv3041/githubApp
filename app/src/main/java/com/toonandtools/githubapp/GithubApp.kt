package com.toonandtools.githubapp


import android.app.Application
import android.util.Log
import androidx.work.Configuration

class GithubApp : Application(), Configuration.Provider {
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.VERBOSE)
            .build()
}
