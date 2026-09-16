package com.example.karmika.feature.customer.booking_confirmation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Storefront
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val CardWhite = Color.White
private val SoftGreen = Color(0xFFE7F6ED)
private val BorderGreen = Color(0xFFD4EBDD)
private val PrimaryGreen = Color(0xFF078B5A)
private val DarkText = Color(0xFF10251D)
private val SecondaryText = Color(0xFF718078)
private val PendingBackground = Color(0xFFFFF2D9)
private val PendingText = Color(0xFFB7791F)

@Composable
fun BookingSummaryCard(
    bookingId: String,
    serviceName: String,
    companyName: String,
    bookingDate: String,
    bookingTime: String,
    address: String,
    employeeCount: Int,
    totalAmount: Int,
    status: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = BorderGreen,
                shape = RoundedCornerShape(20.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        text = "Request Summary",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = DarkText
                    )

                    Text(
                        text = "Your booking information",
                        style = MaterialTheme.typography.bodySmall,
                        color = SecondaryText
                    )
                }

                StatusBadge(status = status)
            }

            SummaryDivider()

            SummaryRow(
                icon = Icons.Rounded.Tag,
                label = "Request ID",
                value = bookingId
            )

            SummaryRow(
                icon = Icons.Rounded.CheckCircle,
                label = "Service",
                value = serviceName
            )

            SummaryRow(
                icon = Icons.Rounded.Storefront,
                label = "Company",
                value = companyName
            )

            SummaryRow(
                icon = Icons.Rounded.CalendarMonth,
                label = "Date",
                value = bookingDate
            )

            SummaryRow(
                icon = Icons.Rounded.Schedule,
                label = "Time",
                value = bookingTime
            )

            SummaryRow(
                icon = Icons.Rounded.LocationOn,
                label = "Location",
                value = address
            )

            SummaryRow(
                icon = Icons.Rounded.Person,
                label = "Professionals",
                value = employeeCount.toString()
            )

            SummaryDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(SoftGreen)
                    .padding(
                        horizontal = 13.dp,
                        vertical = 12.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total Amount",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = DarkText
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "₹$totalAmount",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = PrimaryGreen
                )
            }
        }
    }
}

@Composable
private fun SummaryRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = PrimaryGreen,
            modifier = Modifier.size(19.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = label,
            modifier = Modifier.weight(0.85f),
            style = MaterialTheme.typography.bodyMedium,
            color = SecondaryText
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = value,
            modifier = Modifier.weight(1.15f),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = DarkText
        )
    }
}

@Composable
private fun SummaryDivider() {
    androidx.compose.material3.HorizontalDivider(
        color = BorderGreen,
        thickness = 1.dp
    )
}

@Composable
private fun StatusBadge(
    status: String
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(PendingBackground)
            .padding(
                horizontal = 10.dp,
                vertical = 6.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .size(7.dp)
                .clip(androidx.compose.foundation.shape.CircleShape)
                .background(PendingText)
        )

        Text(
            text = status,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            color = PendingText
        )
    }
}