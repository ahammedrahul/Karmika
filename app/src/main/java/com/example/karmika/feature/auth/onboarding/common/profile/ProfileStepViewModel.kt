package com.example.karmika.feature.auth.onboarding.common.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karmika.core.data.session.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileStepViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProfileStepUiState()
    )

    val uiState: StateFlow<ProfileStepUiState> =
        _uiState.asStateFlow()

    init {
        // Collect user profile data saved during register / login
        viewModelScope.launch {
            userSession.currentUser.collect { user ->
                if (user.fullName.isNotBlank() || user.email.isNotBlank()) {
                    _uiState.value = _uiState.value.copy(
                        fullName = user.fullName,
                        email = user.email,
                        phone = user.phone
                    )
                }
            }
        }
    }

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
    // PROFILE IMAGE URL
    // =====================================================

    fun onProfileImageUrlChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            profileImageUrl = value
        )
    }

    // =====================================================
    // PROFILE PHOTO URI
    // =====================================================

    fun onPhotoSelected(uri: Uri?) {
        _uiState.value = _uiState.value.copy(
            selectedPhotoUri = uri
        )
    }
}