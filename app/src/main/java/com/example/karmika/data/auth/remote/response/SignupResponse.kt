package com.example.karmika.data.auth.remote.response

data class SignupData(
    val user: SignupUser
)

data class SignupUser(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val role: String?,
    val onboardingCompleted: Boolean
)
