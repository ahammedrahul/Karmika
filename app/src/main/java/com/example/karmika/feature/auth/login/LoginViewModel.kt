package com.example.karmika.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karmika.core.data.network.KarmikaApi
import com.example.karmika.core.data.session.UserSession
import com.example.karmika.data.auth.remote.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val karmikaApi: KarmikaApi,
    private val userSession: UserSession
) : ViewModel() {

    private val _uiState =
        kotlinx.coroutines.flow.MutableStateFlow(LoginUiState())

    val uiState: kotlinx.coroutines.flow.StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun onEmailChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            email = value,
            emailError = null,
            errorMessage = null
        )
    }

    fun onPasswordChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            password = value,
            passwordError = null,
            errorMessage = null
        )
    }

    fun login() {
        val currentState = _uiState.value

        var emailError: String? = null
        var passwordError: String? = null

        if (currentState.email.isBlank()) {
            emailError = "Email is required"
        } else if (
            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(currentState.email.trim())
                .matches()
        ) {
            emailError = "Enter a valid email"
        }

        if (currentState.password.isBlank()) {
            passwordError = "Password is required"
        }

        _uiState.value = currentState.copy(
            emailError = emailError,
            passwordError = passwordError,
            errorMessage = null
        )

        if (emailError != null || passwordError != null) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                loginSuccessful = false,
                errorMessage = null
            )

            try {
                val response = karmikaApi.login(
                    LoginRequest(
                        identifier = currentState.email.trim(),
                        password = currentState.password
                    )
                )

                val loginData = response.data

                if (!response.success || loginData == null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = response.message
                            ?: "Login failed. Please try again."
                    )
                    return@launch
                }

                if (loginData.isBlocked) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isBlocked = true,
                        blockReason = loginData.blockReason,
                        blockedUntil = loginData.blockedUntil,
                        blockedAt = loginData.blockedAt,
                        errorMessage = loginData.blockReason
                            ?: "Your account is blocked."
                    )
                    return@launch
                }

                val user = loginData.user

                if (loginData.token.isNullOrBlank()) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Login response did not contain an authentication token."
                    )
                    return@launch
                }

                userSession.updateUser(
                    fullName = user?.name,
                    email = user?.email ?: currentState.email.trim(),
                    role = user?.role,
                    token = loginData.token
                )

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    loginSuccessful = true,
                    token = loginData.token,
                    userId = user?.id,
                    userName = user?.name,
                    userEmail = user?.email,
                    username = user?.username,
                    role = user?.role,
                    onboardingCompleted = user?.onboardingCompleted ?: false,
                    isBlocked = false,
                    blockReason = null,
                    blockedUntil = null,
                    blockedAt = null
                )

            } catch (e: HttpException) {
                val message = when (e.code()) {
                    401 -> "Invalid email or password."
                    403 -> "Your account does not have permission to log in."
                    else -> "Login failed. Please try again."
                }

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = message
                )

            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Unable to connect. Check your internet connection."
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                        ?: "An unexpected error occurred."
                )
            }
        }
    }
}