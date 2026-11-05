package com.example.karmika.feature.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.karmika.core.common.model.UserRole
import com.example.karmika.core.data.session.UserSession
import com.example.karmika.domain.auth.usecase.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase,
    private val userSession: UserSession
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    // FULL NAME
    fun onFullNameChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            fullName = value,
            fullNameError = null,
            generalError = null
        )
    }

    // PHONE
    fun onPhoneChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            phone = value,
            phoneError = null,
            generalError = null
        )
    }

    // EMAIL
    fun onEmailChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            email = value,
            emailError = null,
            generalError = null
        )
    }

    // PASSWORD
    fun onPasswordChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            password = value,
            passwordError = null,
            confirmPasswordError = null,
            generalError = null
        )
    }

    // CONFIRM PASSWORD
    fun onConfirmPasswordChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = value,
            confirmPasswordError = null,
            generalError = null
        )
    }

    // ROLE
    fun onRoleSelected(role: UserRole) {
        _uiState.value = _uiState.value.copy(
            selectedRole = role
        )
    }

    // REGISTER WITH BACKEND
    fun register() {
        val state = _uiState.value

        if (state.isLoading) return

        val fullNameError = validateFullName(state.fullName)
        val emailError = validateEmail(state.email)
        val phoneError = validatePhone(state.phone)
        val passwordError = validatePassword(state.password)
        val confirmPasswordError = validateConfirmPassword(
            state.password,
            state.confirmPassword
        )

        if (
            fullNameError != null ||
            emailError != null ||
            phoneError != null ||
            passwordError != null ||
            confirmPasswordError != null
        ) {
            _uiState.value = state.copy(
                fullNameError = fullNameError,
                emailError = emailError,
                phoneError = phoneError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError,
                generalError = null
            )
            return
        }

        // Cache registration details locally so subsequent onboarding steps reflect them
        userSession.updateUser(
            fullName = state.fullName.trim(),
            email = state.email.trim(),
            phone = state.phone.trim(),
            role = state.selectedRole?.name
        )

        _uiState.value = state.copy(
            isLoading = true,
            generalError = null,
            registrationSuccessful = false
        )

        viewModelScope.launch {
            signupUseCase(
                name = state.fullName.trim(),
                email = state.email.trim(),
                phoneNumber = state.phone.trim(),
                password = state.password
            ).fold(
                onSuccess = { result ->
                    userSession.updateUser(
                        fullName = result.name ?: state.fullName.trim(),
                        email = result.email ?: state.email.trim(),
                        phone = result.phoneNumber ?: state.phone.trim(),
                        role = result.role?.name ?: state.selectedRole?.name
                    )
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        registrationSuccessful = true,
                        generalError = null
                    )
                },
                onFailure = { throwable ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        registrationSuccessful = false,
                        generalError = throwable.message
                            ?: "Registration failed. Please try again."
                    )
                }
            )
        }
    }

    fun clearRegistrationSuccess() {
        _uiState.value = _uiState.value.copy(
            registrationSuccessful = false
        )
    }

    // VALIDATION
    private fun validateFullName(value: String): String? {
        return when {
            value.isBlank() -> "Full name is required"
            value.trim().length < 2 -> "Enter a valid name"
            else -> null
        }
    }

    private fun validateEmail(value: String): String? {
        return when {
            value.isBlank() -> "Email is required"
            !Patterns.EMAIL_ADDRESS.matcher(value.trim()).matches() ->
                "Enter a valid email address"
            else -> null
        }
    }

    private fun validatePhone(value: String): String? {
        return when {
            value.isBlank() -> "Phone number is required"
            value.filter { it.isDigit() }.length < 10 ->
                "Enter a valid phone number"
            else -> null
        }
    }

    private fun validatePassword(value: String): String? {
        return when {
            value.isBlank() -> "Password is required"
            value.length < 8 ->
                "Password must contain at least 8 characters"
            else -> null
        }
    }

    private fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ): String? {
        return when {
            confirmPassword.isBlank() ->
                "Please confirm your password"
            password != confirmPassword ->
                "Passwords do not match"
            else -> null
        }
    }

    // FACTORY
    class Factory(
        private val signupUseCase: SignupUseCase,
        private val userSession: UserSession
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>
        ): T {
            if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
                return RegisterViewModel(
                    signupUseCase,
                    userSession
                ) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}