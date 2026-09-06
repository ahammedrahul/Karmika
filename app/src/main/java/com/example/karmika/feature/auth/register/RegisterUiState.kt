package com.example.karmika.feature.auth.register

import com.example.karmika.core.common.model.UserRole

data class RegisterUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val selectedRole: UserRole = UserRole.HOUSEHOLD,

    val isLoading: Boolean = false,

    val fullNameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,

    val generalError: String? = null,

    val registrationSuccessful: Boolean = false
)