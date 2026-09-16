package com.example.karmika.feature.customer.booking
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.core.ui.designsystem.KarmikaColors
import com.example.karmika.domain.customer.model.Booking

@Composable
fun BookingCard(
    booking: Booking,
    onViewDetailsClick: (Booking) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = KarmikaColors.LightSurface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = KarmikaColors.Border
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // ==============================================
            // HEADER: SERVICE NAME AND STATUS BADGE
            // ==============================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = booking.serviceName,
                    modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = KarmikaColors.LightTextPrimary
                )

                Spacer(modifier = Modifier.width(8.dp))

                StatusBadge(status = booking.status)
            }

            Spacer(modifier = Modifier.height(6.dp))

            // ==============================================
            // COMPANY NAME WITH ICON
            // ==============================================

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = KarmikaColors.LightTextSecondary,
                    modifier = Modifier.size(15.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = booking.companyName,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = KarmikaColors.LightTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ==============================================
            // EXPECTED ARRIVAL: VERTICALLY ALIGNED DATE & TIME
            // ==============================================

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(KarmikaColors.PrimaryLight.copy(alpha = 0.3f))
                    .padding(12.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "EXPECTED ARRIVAL",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                        color = KarmikaColors.Primary
                    )

                    // Date Row
                    ArrivalDetailRow(
                        icon = Icons.Outlined.CalendarToday,
                        label = "Date",
                        value = booking.arrivalDate
                    )

                    // Time Row
                    ArrivalDetailRow(
                        icon = Icons.Outlined.AccessTime,
                        label = "Time",
                        value = booking.arrivalTime
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(
                color = KarmikaColors.Border.copy(alpha = 0.6f),
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // ==============================================
            // PRICE & VIEW DETAILS ACTION
            // ==============================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total Price",
                        fontSize = 11.sp,
                        color = KarmikaColors.LightTextSecondary
                    )

                    Text(
                        text = "₹${booking.totalAmount}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = KarmikaColors.LightTextPrimary
                    )
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onViewDetailsClick(booking) }
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "View Details",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = KarmikaColors.Primary
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "View booking details",
                        tint = KarmikaColors.Primary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

// ==========================================================
// ARRIVAL DETAIL ROW (VERTICAL STACK ITEM)
// ==========================================================

@Composable
private fun ArrivalDetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(KarmikaColors.LightSurface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = KarmikaColors.Primary,
                modifier = Modifier.size(13.dp)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "$label: ",
            fontSize = 12.sp,
            color = KarmikaColors.LightTextSecondary
        )

        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = KarmikaColors.LightTextPrimary
        )
    }
}

// ==========================================================
// STATUS BADGE
// ==========================================================

@Composable
private fun StatusBadge(
    status: String
) {
    val backgroundColor = when (status) {
        "Confirmed", "Completed" -> KarmikaColors.SuccessLight
        "Pending" -> KarmikaColors.WarningLight
        "Cancelled" -> KarmikaColors.ErrorLight
        else -> KarmikaColors.PrimaryLight
    }

    val textColor = when (status) {
        "Confirmed", "Completed" -> KarmikaColors.Success
        "Pending" -> KarmikaColors.Warning
        "Cancelled" -> KarmikaColors.Error
        else -> KarmikaColors.Primary
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
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