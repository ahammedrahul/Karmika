package com.example.karmika.feature.auth.register

import com.example.karmika.core.common.model.UserRole

data class RegisterUiState(

    // User information
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",

    // Password
    val password: String = "",
    val confirmPassword: String = "",

    // Role
    val selectedRole: UserRole = UserRole.HOUSEHOLD,

    // Loading
    val isLoading: Boolean = false,

    // Validation errors
    val fullNameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,

    // General error
    val generalError: String? = null,

    // Registration result
    val registrationSuccessful: Boolean = false
)