package com.example.karmika.core.presentation.health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.karmika.core.data.repository.HealthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HealthUiState(
    val isLoading: Boolean = false,
    val status: String? = null,
    val error: String? = null
)

@HiltViewModel
class HealthViewModel @Inject constructor(
    private val repository: HealthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HealthUiState())
    val uiState: StateFlow<HealthUiState> = _uiState.asStateFlow()

    fun checkBackendHealth() {
        viewModelScope.launch {
            _uiState.value = HealthUiState(isLoading = true)

            try {
                val response = repository.checkHealth()

                _uiState.value = if (response.success && response.data != null) {
                    HealthUiState(status = response.data.status)
                } else {
                    HealthUiState(
                        error = response.message ?: "Backend health check failed"
                    )
                }
            } catch (exception: Exception) {
                _uiState.value = HealthUiState(
                    error = exception.localizedMessage
                        ?: "Unable to connect to backend"
                )
            }
        }
    }
}