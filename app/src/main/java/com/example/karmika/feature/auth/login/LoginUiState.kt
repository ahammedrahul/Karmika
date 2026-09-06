package com.example.karmika.feature.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",

    val emailError: String? = null,
    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val loginSuccessful: Boolean = false
)