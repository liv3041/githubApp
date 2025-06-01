package com.toonandtools.auth


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toonandtools.auth.data.GoogleAuthClient

class AuthViewModelFactory(
    private val googleAuthClient: GoogleAuthClient
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(googleAuthClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
