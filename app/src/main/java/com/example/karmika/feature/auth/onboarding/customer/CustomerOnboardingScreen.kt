package com.example.karmika.feature.auth.onboarding.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.R

private val PrimaryGreen = Color(0xFF00966D)
private val DarkText = Color(0xFF182236)
private val SecondaryText = Color(0xFF71829D)
private val BorderColor = Color(0xFFD9E1EA)
private val BackgroundColor = Color(0xFFFFFFFF)

@Composable
fun CustomerOnboardingScreen(
    viewModel: CustomerOnboardingViewModel,
    onBack: () -> Unit,
    onContinue: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        color = BackgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header & Stepper Region
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                // Title Header

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Karmika Logo",
                        modifier = Modifier.size(32.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Karmika Account Setup",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Progress Stepper Bar
                OnboardingStepper(currentStep = 3)
            }

            HorizontalDivider(color = Color(0xFFE8ECF1), thickness = 1.dp)

            // Scrollable Form Layout
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(
                    horizontal = 24.dp,
                    vertical = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                item {
                    Text(
                        text = "Your Service Address",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Where should our verified skilled workers provide service?",
                        fontSize = 15.sp,
                        color = SecondaryText
                    )
                }

                // Street Address Input Field
                item {
                    AddressInputField(
                        label = "Street Address / House No.",
                        required = true,
                        value = uiState.streetAddress,
                        onValueChange = viewModel::onStreetAddressChanged,
                        placeholder = "e.g. House No. 42, Sector 17–A",
                        errorMessage = uiState.streetAddressError,
                        enabled = !uiState.isSubmitting
                    )
                }

                // Landmark Input Field
                item {
                    AddressInputField(
                        label = "Apartment, Floor, Landmark (Optional)",
                        required = false,
                        value = uiState.apartment,
                        onValueChange = viewModel::onApartmentChanged,
                        placeholder = "e.g. Near City Garden, 2nd Floor",
                        enabled = !uiState.isSubmitting
                    )
                }

                // City Input Field
                item {
                    AddressInputField(
                        label = "City",
                        required = true,
                        value = uiState.city,
                        onValueChange = viewModel::onCityChanged,
                        placeholder = "e.g. Chandigarh",
                        errorMessage = uiState.cityError,
                        enabled = !uiState.isSubmitting
                    )
                }

                // State Input Field
                item {
                    AddressInputField(
                        label = "State / Region",
                        required = true,
                        value = uiState.state,
                        onValueChange = viewModel::onStateChanged,
                        placeholder = "e.g. Punjab",
                        errorMessage = uiState.stateError,
                        enabled = !uiState.isSubmitting
                    )
                }

                // Postal Code Input Field
                item {
                    AddressInputField(
                        label = "Postal / PIN Code",
                        required = true,
                        value = uiState.postalCode,
                        onValueChange = viewModel::onPostalCodeChanged,
                        placeholder = "e.g. 160017",
                        errorMessage = uiState.postalCodeError,
                        enabled = !uiState.isSubmitting
                    )
                }

                // Country Input Field
                item {
                    AddressInputField(
                        label = "Country",
                        required = true,
                        value = uiState.country,
                        onValueChange = viewModel::onCountryChanged,
                        placeholder = "India",
                        enabled = !uiState.isSubmitting
                    )
                }

                // Action Buttons inside LazyColumn
                item {
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = onBack,
                            enabled = !uiState.isSubmitting,
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, BorderColor),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(0xFF52627A)
                            ),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Back", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                        }

                        Button(
                            onClick = {
                                if (viewModel.validateAddress()) {
                                    onContinue()
                                }
                            },
                            enabled = !uiState.isSubmitting,
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryGreen,
                                contentColor = Color.White,
                                disabledContainerColor = PrimaryGreen.copy(alpha = 0.7f)
                            ),
                            modifier = Modifier.height(48.dp)
                        ) {
                            if (uiState.isSubmitting) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Saving...",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            } else {
                                Text(
                                    text = "Complete Setup",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "Complete Setup",
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Reusable Input Field Component
@Composable
private fun AddressInputField(
    label: String,
    required: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    errorMessage: String? = null,
    enabled: Boolean = true
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF334155)
            )
            if (required) {
                Text(
                    text = " *",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE53935)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = errorMessage != null,
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 14.sp,
                    color = Color(0xFF94A3B8)
                )
            },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = BorderColor,
                errorBorderColor = MaterialTheme.colorScheme.error,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}

// Custom Stepper Component
@Composable
private fun OnboardingStepper(currentStep: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StepNode(
            label = "Select Role",
            isCompleted = currentStep > 1,
            isCurrent = currentStep == 1,
            stepNumber = "1",
            modifier = Modifier.weight(1f)
        )

        StepConnector(
            isActive = currentStep > 1,
            modifier = Modifier.padding(bottom = 14.dp)
        )

        StepNode(
            label = "Profile",
            isCompleted = currentStep > 2,
            isCurrent = currentStep == 2,
            stepNumber = "2",
            modifier = Modifier.weight(1f)
        )

        StepConnector(
            isActive = currentStep > 2,
            modifier = Modifier.padding(bottom = 14.dp)
        )

        StepNode(
            label = "Address",
            isCompleted = currentStep > 3,
            isCurrent = currentStep == 3,
            stepNumber = "3",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun StepNode(
    label: String,
    isCompleted: Boolean,
    isCurrent: Boolean,
    stepNumber: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = if (isCurrent || isCompleted) FontWeight.Bold else FontWeight.Normal,
            color = if (isCurrent || isCompleted) DarkText else SecondaryText
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(
                    when {
                        isCompleted || isCurrent -> PrimaryGreen
                        else -> Color(0xFFF1F5F9)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            } else {
                Text(
                    text = stepNumber,
                    color = if (isCurrent) Color.White else Color(0xFF64748B),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun StepConnector(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(36.dp)
            .height(2.dp)
            .background(if (isActive) PrimaryGreen else Color(0xFFCBD5E1))
    )
}