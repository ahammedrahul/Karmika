package com.example.karmika.feature.customer.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.CleaningServices
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.feature.customer.booking.components.AddressSelectionSection
import com.example.karmika.feature.customer.booking.components.BookingNotesField
import com.example.karmika.feature.customer.booking.components.BookingPriceSummary
import com.example.karmika.feature.customer.booking.components.DateTimeSelectionSection
import com.example.karmika.feature.customer.booking.components.EmployeeCountSection

private val PageBackground = Color(0xFFF7FCF9)
private val SurfaceWhite = Color.White
private val SoftGreen = Color(0xFFE8F6EE)
private val BorderGreen = Color(0xFFD7EDE1)
private val PrimaryGreen = Color(0xFF078B5A)
private val DarkText = Color(0xFF10251D)
private val SecondaryText = Color(0xFF004D40)
private val DisabledGreen = Color(0xFFA8D8C0)

@Composable
fun BookingFormScreen(
    serviceId: String,
    onBackClick: () -> Unit,
    onBookingConfirmed: () -> Unit
) {
    val serviceName = "Home Cleaning"
    val companyName = "Karmika Home Experts"
    val servicePrice = 899

    var selectedAddress by remember {
        mutableStateOf("123 Main Street, Bengaluru")
    }

    var selectedDate by remember {
        mutableStateOf("")
    }

    var selectedTime by remember {
        mutableStateOf("")
    }

    var employeeCount by remember {
        mutableIntStateOf(1)
    }

    var bookingNotes by remember {
        mutableStateOf("")
    }

    val totalAmount = servicePrice * employeeCount

    val isFormValid =
        selectedAddress.isNotBlank() &&
                selectedDate.isNotBlank() &&
                selectedTime.isNotBlank() &&
                employeeCount > 0

    Scaffold(
        containerColor = PageBackground,
        topBar = {
            BookingHeader(
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            BookingBottomBar(
                totalAmount = totalAmount,
                isFormValid = isFormValid,
                onBookingConfirmed = onBookingConfirmed
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                BookingProgressHeader()

                BookingServiceCard(
                    serviceId = serviceId,
                    serviceName = serviceName,
                    companyName = companyName,
                    servicePrice = servicePrice
                )

                AddressSelectionSection(
                    address = selectedAddress,
                    onChangeAddressClick = {
                        selectedAddress =
                            if (selectedAddress == "123 Main Street, Bengaluru") {
                                "45 Residency Road, Bengaluru"
                            } else {
                                "123 Main Street, Bengaluru"
                            }
                    }
                )

                DateTimeSelectionSection(
                    selectedDate = selectedDate,
                    selectedTime = selectedTime,
                    onDateSelected = {
                        selectedDate = it
                    },
                    onTimeSelected = {
                        selectedTime = it
                    }
                )

                EmployeeCountSection(
                    employeeCount = employeeCount,
                    onEmployeeCountChange = {
                        employeeCount = it
                    }
                )

                BookingNotesField(
                    notes = bookingNotes,
                    onNotesChanged = {
                        bookingNotes = it
                    }
                )

                BookingPriceSummary(
                    servicePrice = servicePrice,
                    employeeCount = employeeCount,
                    totalAmount = totalAmount
                )

                BookingSafetyNotice()

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun BookingHeader(
    onBackClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = SurfaceWhite,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(SoftGreen)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = PrimaryGreen,
                    modifier = Modifier.size(21.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Request Booking",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = DarkText
                )

                Text(
                    text = "Complete the details below",
                    style = MaterialTheme.typography.bodySmall,
                    color = SecondaryText
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(SoftGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = "Booking form",
                    tint = PrimaryGreen,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

@Composable
private fun BookingProgressHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = BorderGreen,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Booking details",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = DarkText
                    )

                    Text(
                        text = "Provide the required information",
                        style = MaterialTheme.typography.bodySmall,
                        color = SecondaryText
                    )
                }

                Text(
                    text = "Step 1 of 1",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = PrimaryGreen
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BorderGreen)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(PrimaryGreen)
                )
            }

            Text(
                text = "Your request will be reviewed by the service provider.",
                style = MaterialTheme.typography.bodySmall,
                color = SecondaryText
            )
        }
    }
}

@Composable
private fun BookingServiceCard(
    serviceId: String,
    serviceName: String,
    companyName: String,
    servicePrice: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = BorderGreen,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = SoftGreen
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SurfaceWhite),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.CleaningServices,
                    contentDescription = serviceName,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = serviceName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = DarkText
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Storefront,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(14.dp)
                    )

                    Text(
                        text = companyName,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = PrimaryGreen,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Text(
                    text = "Service ID: $serviceId",
                    style = MaterialTheme.typography.labelSmall,
                    color = SecondaryText
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Starting from",
                    style = MaterialTheme.typography.labelSmall,
                    color = SecondaryText
                )

                Text(
                    text = "₹$servicePrice",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = PrimaryGreen
                )
            }
        }
    }
}

@Composable
private fun BookingSafetyNotice() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(SoftGreen)
            .border(0.5.dp, BorderGreen, RoundedCornerShape(14.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Rounded.Security,
            contentDescription = "Secure booking",
            tint = PrimaryGreen,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "Safe and reliable service",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = DarkText
            )

            Text(
                text = "Your request is shared securely with the service provider.",
                style = MaterialTheme.typography.bodySmall,
                color = SecondaryText
            )
        }

        Icon(
            imageVector = Icons.Rounded.Info,
            contentDescription = "Information",
            tint = PrimaryGreen,
            modifier = Modifier.size(18.dp)
        )
    }
}

@Composable
private fun BookingBottomBar(
    totalAmount: Int,
    isFormValid: Boolean,
    onBookingConfirmed: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = SurfaceWhite,
        shadowElevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.8f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Total payable",
                    style = MaterialTheme.typography.labelMedium,
                    color = SecondaryText
                )

                Text(
                    text = "₹$totalAmount",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = PrimaryGreen
                )
            }

            Button(
                onClick = onBookingConfirmed,
                enabled = isFormValid,
                modifier = Modifier
                    .weight(1.2f)
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryGreen,
                    contentColor = Color.White,
                    disabledContainerColor = DisabledGreen,
                    disabledContentColor = Color.White.copy(alpha = 0.75f)
                ),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Confirm Request",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}