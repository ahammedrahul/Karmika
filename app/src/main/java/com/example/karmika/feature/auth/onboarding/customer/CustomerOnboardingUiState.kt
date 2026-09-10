data class CustomerOnboardingUiState(
    val streetAddress: String = "",
    val streetAddressError: String? = null,
    val apartment: String = "",
    val city: String = "",
    val cityError: String? = null,
    val state: String = "",
    val stateError: String? = null,
    val postalCode: String = "",
    val postalCodeError: String? = null,
    val country: String = "India",
    val isSubmitting: Boolean = false // Add this property
)