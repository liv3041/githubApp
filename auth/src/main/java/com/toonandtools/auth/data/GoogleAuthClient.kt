package com.toonandtools.auth.data

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.toonandtools.auth.domain.AuthResult
import kotlinx.coroutines.tasks.await

class GoogleAuthClient(
    private val activity: Activity,
    private val auth: FirebaseAuth
) {
    private val oneTapClient: SignInClient = Identity.getSignInClient(activity)

    private val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId("662065620713-hi801ch5b2e4heebv6rscipcr9p1lhbj.apps.googleusercontent.com") // Get this from Firebase project settings
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    suspend fun beginSignIn(): IntentSender? {
        return try {
            val result = oneTapClient.beginSignIn(signInRequest).await()
            result.pendingIntent.intentSender
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signInWithGoogle(intent: Intent): AuthResult {
        return try {
            val credential = oneTapClient.getSignInCredentialFromIntent(intent)
            val idToken = credential.googleIdToken ?: return AuthResult.Error("No ID token")
            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = auth.signInWithCredential(firebaseCredential).await()
            val user = authResult.user ?: return AuthResult.Error("User not found")
            AuthResult.Success(user.displayName ?: "Unknown")
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign-in failed")
        }
    }
}
