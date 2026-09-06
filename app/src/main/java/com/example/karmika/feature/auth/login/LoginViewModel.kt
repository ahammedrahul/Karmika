package com.example.karmika.feature.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

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


        // Backend will be connected here later.
        //
        // Example later:
        //
        // loginUseCase(
        //     email = currentState.email,
        //     password = currentState.password
        // )
    }
}