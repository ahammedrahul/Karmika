package com.example.karmika.feature.customer.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color Palette
private val PrimaryGreen = Color(0xFF00966D)
private val LightGreenCard = Color(0xFFE6F4EA)
private val DarkText = Color(0xFF182236)
private val SecondaryText = Color(0xFF64748B)
private val CardBorderColor = Color(0xFFD3ECE1)
private val BackgroundColor = Color(0xFFF8FAFC)

// Status Specific Colors
private val StatusPendingBg = Color(0xFFFFF7ED)
private val StatusPendingText = Color(0xFFC2410C)
private val StatusCancelledBg = Color(0xFFFEF2F2)
private val StatusCancelledText = Color(0xFFDC2626)

@Composable
fun CustomerBookingScreen() {
    var selectedTab by remember { mutableStateOf("Upcoming") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Title Header
            item {
                Text(
                    text = "My Bookings",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )
            }

            // Custom Filter Chips
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Upcoming", "Completed", "Cancelled").forEach { tab ->
                        FilterChip(
                            selected = selectedTab == tab,
                            onClick = { selectedTab = tab },
                            label = {
                                Text(
                                    text = tab,
                                    fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = Color.White,
                                labelColor = SecondaryText,
                                selectedContainerColor = PrimaryGreen,
                                selectedLabelColor = Color.White
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = selectedTab == tab,
                                borderColor = Color(0xFFE2E8F0),
                                selectedBorderColor = PrimaryGreen
                            )
                        )
                    }
                }
            }

            // Bookings List
            when (selectedTab) {
                "Upcoming" -> {
                    item {
                        BookingCard(
                            service = "Plumbing Service",
                            worker = "Rajesh Kumar",
                            date = "Tomorrow",
                            time = "10:00 AM",
                            status = "Confirmed"
                        )
                    }
                    item {
                        BookingCard(
                            service = "AC Repair",
                            worker = "Amit Sharma",
                            date = "18 September",
                            time = "2:00 PM",
                            status = "Pending"
                        )
                    }
                }

                "Completed" -> {
                    item {
                        BookingCard(
                            service = "Electrical Service",
                            worker = "Suresh Verma",
                            date = "10 September",
                            time = "11:00 AM",
                            status = "Completed"
                        )
                    }
                }

                else -> {
                    item {
                        BookingCard(
                            service = "Cleaning Service",
                            worker = "Vikram Singh",
                            date = "5 September",
                            time = "3:00 PM",
                            status = "Cancelled"
                        )
                    }
                }
            }
        }
    }
}

// Reusable Light-Green Theme Booking Card
@Composable
private fun BookingCard(
    service: String,
    worker: String,
    date: String,
    time: String,
    status: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = LightGreenCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, CardBorderColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Service Name & Status Badge Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = service,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                StatusBadge(status = status)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Assigned Professional Details
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Worker",
                    tint = PrimaryGreen,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = worker,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkText
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Date & Time Row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.CalendarToday,
                    contentDescription = "Schedule",
                    tint = SecondaryText,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "$date • $time",
                    fontSize = 13.sp,
                    color = SecondaryText
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // View Details Link / Action
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View Details",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

// Custom Dynamic Status Badge
@Composable
private fun StatusBadge(status: String) {
    val (backgroundColor, textColor) = when (status) {
        "Confirmed", "Completed" -> Pair(PrimaryGreen, Color.White)
        "Pending" -> Pair(StatusPendingBg, StatusPendingText)
        "Cancelled" -> Pair(StatusCancelledBg, StatusCancelledText)
        else -> Pair(PrimaryGreen, Color.White)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = status,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}