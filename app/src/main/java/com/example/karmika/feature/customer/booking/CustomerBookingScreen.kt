package com.example.karmika.feature.customer.booking
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.karmika.domain.customer.model.Booking

@Composable
fun CustomerBookingScreen() {

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    var selectedBooking by remember {
        mutableStateOf<Booking?>(null)
    }

    /*
     * Static booking data for UI testing.
     * Every booking has a unique bookingId.
     */
    val bookings = remember {
        listOf(
            // Upcoming booking
            Booking(
                bookingId = "BK1001",
                companyName = "Karmika Home Services",
                serviceName = "AC Repair",
                bookingDate = "15 September 2026",
                bookingTime = "08:30 AM",
                arrivalDate = "20 September 2026",
                arrivalTime = "10:00 AM - 12:00 PM",
                status = "Confirmed",
                totalAmount = 850.00
            ),

            // Upcoming booking
            Booking(
                bookingId = "BK1002",
                companyName = "Reliable Electricians",
                serviceName = "Electrical Wiring",
                bookingDate = "15 September 2026",
                bookingTime = "09:30 AM",
                arrivalDate = "22 September 2026",
                arrivalTime = "02:00 PM - 04:00 PM",
                status = "Pending",
                totalAmount = 1200.00
            ),

            // Completed booking
            Booking(
                bookingId = "BK1003",
                companyName = "Clean Home Services",
                serviceName = "House Cleaning",
                bookingDate = "10 September 2026",
                bookingTime = "10:00 AM",
                arrivalDate = "12 September 2026",
                arrivalTime = "11:00 AM - 01:00 PM",
                status = "Completed",
                totalAmount = 700.00
            ),

            // Cancelled booking
            Booking(
                bookingId = "BK1004",
                companyName = "Karmika Plumbing",
                serviceName = "Pipe Repair",
                bookingDate = "8 September 2026",
                bookingTime = "02:00 PM",
                arrivalDate = "9 September 2026",
                arrivalTime = "03:00 PM - 05:00 PM",
                status = "Cancelled",
                totalAmount = 500.00
            )
        )
    }

    /*
     * Show booking details when a booking is selected.
     */
    if (selectedBooking != null) {

        BookingDetailsScreen(
            booking = selectedBooking!!,
            onBackClick = {
                selectedBooking = null
            }
        )

        return
    }

    val filteredBookings = when (selectedTabIndex) {

        0 -> bookings.filter {
            it.status.equals("Confirmed", ignoreCase = true) ||
                    it.status.equals("Pending", ignoreCase = true)
        }

        1 -> bookings.filter {
            it.status.equals("Completed", ignoreCase = true)
        }

        2 -> bookings.filter {
            it.status.equals("Cancelled", ignoreCase = true)
        }

        else -> bookings
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        /*
         * Booking tabs
         */
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {

            Tab(
                selected = selectedTabIndex == 0,
                onClick = {
                    selectedTabIndex = 0
                },
                text = {
                    Text(text = "Upcoming")
                }
            )

            Tab(
                selected = selectedTabIndex == 1,
                onClick = {
                    selectedTabIndex = 1
                },
                text = {
                    Text(text = "Completed")
                }
            )

            Tab(
                selected = selectedTabIndex == 2,
                onClick = {
                    selectedTabIndex = 2
                },
                text = {
                    Text(text = "Cancelled")
                }
            )
        }

        /*
         * Booking list
         */
        if (filteredBookings.isEmpty()) {

            EmptyBookingMessage()

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(
                    items = filteredBookings,
                    key = {
                        it.bookingId
                    }
                ) { booking ->

                    BookingCard(
                        booking = booking,
                        onViewDetailsClick = {
                            selectedBooking = it
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyBookingMessage() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "No bookings available",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}