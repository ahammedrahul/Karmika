package com.example.karmika.data.auth.remote.request

data class LoginRequest(
    val identifier: String,
    val password: String
)