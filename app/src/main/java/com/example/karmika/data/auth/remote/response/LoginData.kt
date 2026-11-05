package com.example.karmika.data.auth.remote.response

data class LoginData(
    val user: LoginUser? = null,
    val token: String? = null,
    val isBlocked: Boolean = false,
    val blockReason: String? = null,
    val blockedUntil: String? = null,
    val blockedAt: String? = null
)

data class LoginUser(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val username: String? = null,
    val phoneNumber: String? = null,
    val role: String? = null,
    val onboardingCompleted: Boolean = false,
    val accountStatus: String? = null
)