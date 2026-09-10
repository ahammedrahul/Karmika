package com.example.karmika.feature.auth.onboarding.common

import androidx.lifecycle.ViewModel
import com.example.karmika.core.common.model.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {

    private val _selectedRole = MutableStateFlow(UserRole.HOUSEHOLD)

    val selectedRole: StateFlow<UserRole> =
        _selectedRole.asStateFlow()

    fun selectRole(role: UserRole) {
        _selectedRole.value = role
    }
}