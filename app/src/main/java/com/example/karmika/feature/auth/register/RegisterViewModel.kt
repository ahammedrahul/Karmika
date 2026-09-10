package com.example.karmika.feature.auth.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.karmika.core.common.model.UserRole
import com.example.karmika.data.local.LocalUserStorage
import com.example.karmika.data.local.model.LocalUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel(
    private val localUserStorage: LocalUserStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())

    val uiState: StateFlow<RegisterUiState> =
        _uiState.asStateFlow()


    // --------------------------------------------------
    // FULL NAME
    // --------------------------------------------------

    fun onFullNameChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            fullName = value,
            fullNameError = null
        )
    }

    //phone
    fun onPhoneChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            phone = value,
            phoneError = null
        )
    }


    // --------------------------------------------------
    // EMAIL
    // --------------------------------------------------

    fun onEmailChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            email = value,
            emailError = null
        )
    }


    // --------------------------------------------------
    // PASSWORD
    // --------------------------------------------------

    fun onPasswordChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            password = value,
            passwordError = null,
            confirmPasswordError = null
        )
    }


    // --------------------------------------------------
    // CONFIRM PASSWORD
    // --------------------------------------------------

    fun onConfirmPasswordChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            confirmPassword = value,
            confirmPasswordError = null
        )
    }


    // --------------------------------------------------
    // ROLE
    // --------------------------------------------------

    fun onRoleSelected(role: UserRole) {

        _uiState.value = _uiState.value.copy(
            selectedRole = role
        )
    }


    // --------------------------------------------------
    // REGISTER
    // --------------------------------------------------

    fun register() {

        val state = _uiState.value

        val fullNameError =
            validateFullName(state.fullName)

        val emailError =
            validateEmail(state.email)

        val passwordError =
            validatePassword(state.password)

        val confirmPasswordError =
            validateConfirmPassword(
                state.password,
                state.confirmPassword
            )


        // ---------------------------------------------
        // VALIDATION FAILED
        // ---------------------------------------------

        if (
            fullNameError != null ||
            emailError != null ||
            passwordError != null ||
            confirmPasswordError != null
        ) {

            _uiState.value = state.copy(
                fullNameError = fullNameError,
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )

            return
        }


        // ---------------------------------------------
        // SAVE USER LOCALLY
        // ---------------------------------------------

        val user = LocalUser(
            fullName = state.fullName.trim(),
            email = state.email.trim(),
            password = state.password,
            role = state.selectedRole
        )

        localUserStorage.saveUser(user)


        // ---------------------------------------------
        // REGISTRATION SUCCESS
        // ---------------------------------------------

        _uiState.value = state.copy(
            registrationSuccessful = true,
            isLoading = false
        )
    }


    // --------------------------------------------------
    // CLEAR SUCCESS
    // --------------------------------------------------

    fun clearRegistrationSuccess() {

        _uiState.value =
            _uiState.value.copy(
                registrationSuccessful = false
            )
    }


    // --------------------------------------------------
    // VALIDATION
    // --------------------------------------------------

    private fun validateFullName(
        value: String
    ): String? {

        return when {

            value.isBlank() ->
                "Full name is required"

            value.trim().length < 2 ->
                "Enter a valid name"

            else ->
                null
        }
    }


    private fun validateEmail(
        value: String
    ): String? {

        return when {

            value.isBlank() ->
                "Email is required"

            !Patterns.EMAIL_ADDRESS
                .matcher(value.trim())
                .matches() ->
                "Enter a valid email address"

            else ->
                null
        }
    }


    private fun validatePassword(
        value: String
    ): String? {

        return when {

            value.isBlank() ->
                "Password is required"

            value.length < 8 ->
                "Password must contain at least 8 characters"

            else ->
                null
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

            else ->
                null
        }
    }


    // --------------------------------------------------
    // FACTORY
    // --------------------------------------------------

    class Factory(
        private val localUserStorage: LocalUserStorage
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>
        ): T {

            if (
                modelClass.isAssignableFrom(
                    RegisterViewModel::class.java
                )
            ) {

                return RegisterViewModel(
                    localUserStorage
                ) as T
            }

            throw IllegalArgumentException(
                "Unknown ViewModel class"
            )
        }
    }
}