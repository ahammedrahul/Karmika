package com.example.karmika.feature.auth.onboarding.common.profile

import android.net.Uri

data class ProfileStepUiState(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val selectedAvatar: Int = 0,
    val profileImageUrl: String = "",
    val selectedPhotoUri: Uri? = null,
    val isUploading: Boolean = false
)
