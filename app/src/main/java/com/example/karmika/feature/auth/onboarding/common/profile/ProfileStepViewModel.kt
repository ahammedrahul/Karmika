package com.example.karmika.feature.auth.onboarding.common.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileStepViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProfileStepUiState()
    )

    val uiState: StateFlow<ProfileStepUiState> =
        _uiState.asStateFlow()


    // =====================================================
    // REGISTRATION DATA
    // =====================================================

    fun setRegistrationData(
        fullName: String,
        email: String,
        phone: String = ""
    ) {

        _uiState.value = _uiState.value.copy(
            fullName = fullName,
            email = email,
            phone = phone
        )
    }


    // =====================================================
    // AVATAR
    // =====================================================

    fun onAvatarSelected(index: Int) {

        _uiState.value = _uiState.value.copy(
            selectedAvatar = index
        )
    }


    // =====================================================
    // PROFILE IMAGE
    // =====================================================

    fun onProfileImageUrlChanged(
        value: String
    ) {

        _uiState.value = _uiState.value.copy(
            profileImageUrl = value
        )
    }
}