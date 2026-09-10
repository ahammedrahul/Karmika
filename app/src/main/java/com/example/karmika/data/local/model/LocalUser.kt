package com.example.karmika.data.local.model

import com.example.karmika.core.common.model.UserRole

data class LocalUser(
    val fullName: String,
    val email: String,
    val password: String,
    val role: UserRole
)