package com.toonandtools.auth

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toonandtools.auth.data.GoogleAuthClient
import com.toonandtools.auth.domain.AuthResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val googleAuthClient: GoogleAuthClient
) : ViewModel() {

    private val _authResult = MutableStateFlow<AuthResult>(AuthResult.Idle)
//    val authResult = _authResult.asStateFlow()

    private val _navigateToHome = Channel<Unit>()
    val navigateToHome = _navigateToHome.receiveAsFlow()

    fun beginGoogleSignIn(onIntentReady: (IntentSender) -> Unit) {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            val intentSender = googleAuthClient.beginSignIn()
            if (intentSender != null) {
                onIntentReady(intentSender)
            } else {
                _authResult.value = AuthResult.Error("Unable to start sign-in")
            }
        }
    }

    //    fun handleGoogleSignInResult(intent: Intent) {
//        viewModelScope.launch {
//            _authResult.value = googleAuthClient.signInWithGoogle(intent)
//        }
//    }
    fun handleGoogleSignInResult(intent: Intent) {
        viewModelScope.launch {
            val result = googleAuthClient.signInWithGoogle(intent)
            _authResult.value = result

            if (result is AuthResult.Success) {
                _navigateToHome.send(Unit) // Send signal to navigate
            }
        }
    }

}