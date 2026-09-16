package com.example.karmika.feature.customer.booking_confirmation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.HourglassTop
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.feature.customer.booking_confirmation.components.BookingActionButtons
import com.example.karmika.feature.customer.booking_confirmation.components.BookingSuccessIcon
import com.example.karmika.feature.customer.booking_confirmation.components.BookingSummaryCard
import kotlinx.coroutines.delay

// Light Green Palette
private val PageBackground = Color(0xFFF7FCF9)
private val SurfaceWhite = Color.White
private val SoftGreen = Color(0xFFE8F5E9)
private val BorderGreen = Color(0xFFE0F2E9)
private val PrimaryGreen = Color(0xFF2E7D32)
private val DarkText = Color(0xFF10251D)
private val SecondaryText = Color(0xFF6C7A70)
private val PendingBackground = Color(0xFFFFF3E0)
private val PendingText = Color(0xFFE65100)

@Composable
fun BookingConfirmationScreen(
    bookingId: String = "REQ-1001",
    serviceName: String = "Home Cleaning",
    companyName: String = "Karmika Home Experts",
    bookingDate: String = "20 September 2026",
    bookingTime: String = "10:00 AM",
    address: String = "123 Main Street, Bengaluru",
    employeeCount: Int = 2,
    totalAmount: Int = 1798,
    status: String = "PENDING",
    onViewBookingsClick: () -> Unit = {},
    onBackToHomeClick: () -> Unit = {}
) {
    // Sequential Animation Trigger States
    var showHeader by remember { mutableStateOf(false) }
    var showSummary by remember { mutableStateOf(false) }
    var showInfoCard by remember { mutableStateOf(false) }
    var showButtons by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        showHeader = true
        delay(150)
        showSummary = true
        delay(150)
        showInfoCard = true
        delay(100)
        showButtons = true
    }

    Scaffold(
        containerColor = PageBackground,
        topBar = {
            ConfirmationTopBar(
                onBackToHomeClick = onBackToHomeClick
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Animated Success Header
            AnimatedVisibility(
                visible = showHeader,
                enter = fadeIn(animationSpec = tween(400)) + slideInVertically(
                    initialOffsetY = { -40 },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            ) {
                ConfirmationSuccessHeader(status = status)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Animated Booking Summary Card
            AnimatedVisibility(
                visible = showSummary,
                enter = fadeIn(animationSpec = tween(400)) + slideInVertically(
                    initialOffsetY = { 60 },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            ) {
                BookingSummaryCard(
                    bookingId = bookingId,
                    serviceName = serviceName,
                    companyName = companyName,
                    bookingDate = bookingDate,
                    bookingTime = bookingTime,
                    address = address,
                    employeeCount = employeeCount,
                    totalAmount = totalAmount,
                    status = status
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Animated Info Security Card
            AnimatedVisibility(
                visible = showInfoCard,
                enter = fadeIn(animationSpec = tween(350)) + slideInVertically(
                    initialOffsetY = { 40 }
                )
            ) {
                ConfirmationInfoCard()
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Animated Bottom Buttons
            AnimatedVisibility(
                visible = showButtons,
                enter = fadeIn(animationSpec = tween(300)) + slideInVertically(
                    initialOffsetY = { 30 }
                )
            ) {
                BookingActionButtons(
                    onViewBookingsClick = onViewBookingsClick,
                    onBackToHomeClick = onBackToHomeClick
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ConfirmationTopBar(
    onBackToHomeClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = SurfaceWhite,
        shadowElevation = 0.5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackToHomeClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(SoftGreen)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back to home",
                    tint = PrimaryGreen,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Karmika",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    ),
                    color = DarkText
                )

                Text(
                    text = "Booking Confirmation",
                    style = MaterialTheme.typography.labelSmall,
                    color = SecondaryText
                )
            }

            IconButton(
                onClick = onBackToHomeClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF1F5F2))
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close",
                    tint = DarkText,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun ConfirmationSuccessHeader(
    status: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BookingSuccessIcon()

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Request Submitted!",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            color = DarkText,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Your service request has been placed successfully.",
            style = MaterialTheme.typography.bodyMedium,
            color = SecondaryText,
            textAlign = TextAlign.Center
        )

        StatusBadge(status = status)
    }
}

@Composable
private fun StatusBadge(
    status: String
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(PendingBackground)
            .padding(horizontal = 14.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.HourglassTop,
            contentDescription = null,
            tint = PendingText,
            modifier = Modifier.size(14.dp)
        )

        Text(
            text = status.uppercase(),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
            ),
            color = PendingText
        )
    }
}

@Composable
private fun ConfirmationInfoCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceWhite)
            .border(1.dp, BorderGreen, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(SoftGreen),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Security,
                contentDescription = "Secure request",
                tint = PrimaryGreen,
                modifier = Modifier.size(20.dp)
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "Your request is protected",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = DarkText
            )

            Text(
                text = "You can track real-time updates under My Bookings.",
                style = MaterialTheme.typography.bodySmall,
                color = SecondaryText
            )
        }

        Icon(
            imageVector = Icons.Rounded.CheckCircle,
            contentDescription = null,
            tint = PrimaryGreen,
            modifier = Modifier.size(20.dp)
        )
    }
}