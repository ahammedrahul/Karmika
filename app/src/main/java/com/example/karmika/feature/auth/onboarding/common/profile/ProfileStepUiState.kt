package com.example.karmika.feature.auth.onboarding.common.profile

data class ProfileStepUiState(

    val fullName: String = "",

    val email: String = "",

    val phone: String = "",

    val selectedAvatar: Int = 0,

    val profileImageUrl: String = ""
)