package com.example.karmika.domain.customer.model

data class Service(
    val id: String,
    val name: String,
    val description: String,
    val categoryId: String,
    val categoryName: String,
    val companyId: String,
    val companyName: String,
    val price: Int,
    val rating: Double,
    val reviewCount: Int,
    val duration: String,
    val includedItems: List<String> = emptyList()
)