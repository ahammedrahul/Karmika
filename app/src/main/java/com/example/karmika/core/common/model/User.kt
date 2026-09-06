package com.example.karmika.core.common.model

data class User(
    val id: String? = null,
    val fullName: String,
    val email: String,
    val role: UserRole
)