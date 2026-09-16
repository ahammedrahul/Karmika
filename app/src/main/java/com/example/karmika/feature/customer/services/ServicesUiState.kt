package com.example.karmika.feature.customer.services

import com.example.karmika.domain.customer.model.Service

data class ServicesUiState(
    val categoryId: String = "all",
    val categoryName: String = "All Services",
    val services: List<Service> = emptyList(),
    val searchQuery: String = "",
    val selectedSortOption: ServiceSortOption = ServiceSortOption.RELEVANCE,
    val isFilterMenuVisible: Boolean = false
)

enum class ServiceSortOption {
    RELEVANCE,
    PRICE_LOW_TO_HIGH,
    PRICE_HIGH_TO_LOW,
    RATING_HIGH_TO_LOW
}