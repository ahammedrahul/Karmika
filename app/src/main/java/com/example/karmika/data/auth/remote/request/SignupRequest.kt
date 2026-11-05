package com.example.karmika.data.auth.remote.request

data class SignupRequest(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val password: String
)
