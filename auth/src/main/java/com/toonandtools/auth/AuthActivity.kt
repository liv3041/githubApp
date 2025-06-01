package com.toonandtools.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.auth.FirebaseAuth
import com.toonandtools.auth.data.GoogleAuthClient
import com.toonandtools.auth.ui.theme.GithubAppTheme

class AuthActivity : ComponentActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AuthActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val googleAuthClient = GoogleAuthClient(this, FirebaseAuth.getInstance())

        setContent {
            GithubAppTheme {
                AuthEntryScreen(googleAuthClient)

            }
        }
    }
}
