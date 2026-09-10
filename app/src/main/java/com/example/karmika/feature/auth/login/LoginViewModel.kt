package com.example.karmika.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.karmika.data.local.LocalUserStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val localUserStorage: LocalUserStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()


    fun onEmailChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            email = value,
            emailError = null
        )
    }


    fun onPasswordChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            password = value,
            passwordError = null
        )
    }


    fun login() {

        val currentState = _uiState.value

        var hasError = false

        var emailError: String? = null
        var passwordError: String? = null


        if (currentState.email.isBlank()) {

            emailError = "Email is required"

            hasError = true

        } else if (
            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(currentState.email)
                .matches()
        ) {

            emailError = "Enter a valid email"

            hasError = true
        }


        if (currentState.password.isBlank()) {

            passwordError = "Password is required"

            hasError = true
        }


        _uiState.value = currentState.copy(
            emailError = emailError,
            passwordError = passwordError
        )


        if (hasError) return


        // -----------------------------------------------------
        // LOCAL LOGIN
        // -----------------------------------------------------

        val savedUser = localUserStorage.getUser()


        if (savedUser == null) {

            _uiState.value = _uiState.value.copy(
                emailError = "No registered account found"
            )

            return
        }


        if (
            savedUser.email.trim() !=
            currentState.email.trim()
        ) {

            _uiState.value = _uiState.value.copy(
                emailError = "Account not found"
            )

            return
        }


        if (
            savedUser.password !=
            currentState.password
        ) {

            _uiState.value = _uiState.value.copy(
                passwordError = "Incorrect password"
            )

            return
        }


        // Login successful

        _uiState.value = _uiState.value.copy(
            loginSuccessful = true
        )
    }


    // ---------------------------------------------------------
    // VIEWMODEL FACTORY
    // ---------------------------------------------------------

    class Factory(
        private val localUserStorage: LocalUserStorage
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>
        ): T {

            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {

                return LoginViewModel(
                    localUserStorage
                ) as T
            }

            throw IllegalArgumentException(
                "Unknown ViewModel class"
            )
        }
    }
}