package com.example.karmika.domain.customer.model

data class Booking(
    val bookingId: String,
    val companyName: String,
    val serviceName: String,

    // Booking creation date and time
    val bookingDate: String,
    val bookingTime: String,

    // Expected service arrival date and time
    val arrivalDate: String,
    val arrivalTime: String,

    val status: String,
    val totalAmount: Double
)