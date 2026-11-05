package com.example.karmika.feature.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",

    val emailError: String? = null,
    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val loginSuccessful: Boolean = false,

    val errorMessage: String? = null,

    // Authenticated session
    val token: String? = null,
    val userId: String? = null,
    val userName: String? = null,
    val userEmail: String? = null,
    val username: String? = null,
    val role: String? = null,
    val onboardingCompleted: Boolean = false,

    // Account blocking
    val isBlocked: Boolean = false,
    val blockReason: String? = null,
    val blockedUntil: String? = null,
    val blockedAt: String? = null
)