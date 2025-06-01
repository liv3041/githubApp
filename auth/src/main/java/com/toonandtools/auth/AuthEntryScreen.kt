package com.toonandtools.auth

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
import androidx.compose.runtime.LaunchedEffect

import androidx.lifecycle.viewmodel.compose.viewModel
import com.toonandtools.auth.data.GoogleAuthClient

@Composable
fun AuthEntryScreen(googleAuthClient: GoogleAuthClient) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(googleAuthClient))
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            result.data?.let { intent ->
                viewModel.handleGoogleSignInResult(intent)
            }
        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.navigateToHome.collect {
            // Launch HomeActivity from the `main` module
            val homeIntent = Intent(context, Class.forName("com.toonandtools.main.HomeActivity"))
            context.startActivity(homeIntent)
            if (context is Activity) {
                context.finish() // Optional: close the AuthActivity
            }
        }
    }

    AuthScreen(
        onSignInClicked = {
            viewModel.beginGoogleSignIn { intentSender ->
                launcher.launch(IntentSenderRequest.Builder(intentSender).build())
            }
        }
    )
}
