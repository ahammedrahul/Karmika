package com.example.karmika.feature.auth.onboarding.customer

import CustomerOnboardingUiState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CustomerOnboardingViewModel : ViewModel() {

    // =====================================================
    // UI STATE
    // =====================================================

    private val _uiState = MutableStateFlow(
        CustomerOnboardingUiState()
    )

    val uiState: StateFlow<CustomerOnboardingUiState> =
        _uiState.asStateFlow()


    // =====================================================
    // STREET ADDRESS
    // =====================================================

    fun onStreetAddressChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            streetAddress = value,
            streetAddressError = null
        )
    }


    // =====================================================
    // APARTMENT / FLOOR / LANDMARK
    // =====================================================

    fun onApartmentChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            apartment = value
        )
    }


    // =====================================================
    // CITY
    // =====================================================

    fun onCityChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            city = value,
            cityError = null
        )
    }


    // =====================================================
    // STATE / REGION
    // =====================================================

    fun onStateChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            state = value,
            stateError = null
        )
    }


    // =====================================================
    // POSTAL / PIN CODE
    // =====================================================

    fun onPostalCodeChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            postalCode = value,
            postalCodeError = null
        )
    }


    // =====================================================
    // COUNTRY
    // =====================================================

    fun onCountryChanged(value: String) {

        _uiState.value = _uiState.value.copy(
            country = value
        )
    }


    // =====================================================
    // VALIDATE ADDRESS
    // =====================================================

    fun validateAddress(): Boolean {

        val currentState = _uiState.value

        var streetAddressError: String? = null
        var cityError: String? = null
        var stateError: String? = null
        var postalCodeError: String? = null


        // -------------------------------------------------
        // STREET ADDRESS
        // -------------------------------------------------

        if (currentState.streetAddress.isBlank()) {

            streetAddressError =
                "Street address is required"
        }


        // -------------------------------------------------
        // CITY
        // -------------------------------------------------

        if (currentState.city.isBlank()) {

            cityError =
                "City is required"
        }


        // -------------------------------------------------
        // STATE / REGION
        // -------------------------------------------------

        if (currentState.state.isBlank()) {

            stateError =
                "State / Region is required"
        }


        // -------------------------------------------------
        // POSTAL / PIN CODE
        // -------------------------------------------------

        if (currentState.postalCode.isBlank()) {

            postalCodeError =
                "Postal / PIN code is required"

        } else if (
            !currentState.postalCode.all { it.isDigit() }
        ) {

            postalCodeError =
                "Enter a valid PIN code"
        }


        // =================================================
        // UPDATE UI STATE
        // =================================================

        _uiState.value = currentState.copy(

            streetAddressError =
                streetAddressError,

            cityError =
                cityError,

            stateError =
                stateError,

            postalCodeError =
                postalCodeError
        )


        // =================================================
        // RETURN VALIDATION RESULT
        // =================================================

        return streetAddressError == null &&
                cityError == null &&
                stateError == null &&
                postalCodeError == null
    }
}