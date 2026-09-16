package com.example.karmika.feature.customer.profile

data class CustomerProfileUiState(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val profileImageUrl: String? = null,

    val savedAddressesCount: Int = 0,
    val favoriteServicesCount: Int = 0,
    val favoriteCompaniesCount: Int = 0,

    val isLoading: Boolean = false,
    val errorMessage: String? = null
)