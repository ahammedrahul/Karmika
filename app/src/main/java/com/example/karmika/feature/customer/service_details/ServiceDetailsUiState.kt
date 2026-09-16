package com.example.karmika.feature.customer.service_details

data class ServiceDetailsUiState(
    val serviceId: String = "",
    val serviceName: String = "",
    val description: String = "",
    val categoryName: String = "",
    val companyName: String = "",
    val price: Int = 0,
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val duration: String = "",
    val includedItems: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)