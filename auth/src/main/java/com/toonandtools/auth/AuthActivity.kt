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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
            }
        }
        enableEdgeToEdge()
        val googleAuthClient = GoogleAuthClient(this, FirebaseAuth.getInstance())

        setContent {
            GithubAppTheme {
                AuthEntryScreen(googleAuthClient)

            }
        }
    }
}
