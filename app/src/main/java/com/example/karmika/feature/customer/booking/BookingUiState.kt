package com.example.karmika.feature.customer.booking

data class BookingUiState(
    val selectedDate: String = "",
    val selectedTime: String = "",
    val selectedAddress: String = "",
    val notes: String = "",
    val employeeCount: Int = 1,
    val totalPrice: Int = 0
)