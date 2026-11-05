package com.example.karmika.domain.auth.repository

import com.example.karmika.core.common.model.UserRole

interface AuthRepository {

    suspend fun signup(
        name: String,
        email: String,
        phoneNumber: String,
        password: String
    ): Result<SignupResult>
}

data class SignupResult(
    val userId: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val role: UserRole?,
    val onboardingCompleted: Boolean
)
