package com.toonandtools.auth.domain

sealed class AuthResult {
    data class Success(val username: String): AuthResult()
    data class Error(val message: String): AuthResult()
    object Loading : AuthResult()
    object Idle : AuthResult()
}
