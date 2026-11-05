package com.example.karmika.feature.auth.onboarding.customer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.PinDrop
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.core.ui.designsystem.KarmikaColors
import com.example.karmika.core.ui.designsystem.KarmikaDimensions
import com.example.karmika.core.ui.designsystem.KarmikaTypography
import com.example.karmika.feature.auth.onboarding.common.components.OnboardingHeader
import com.example.karmika.feature.auth.onboarding.common.components.OnboardingStepper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomerOnboardingScreen(
    viewModel: CustomerOnboardingViewModel,
    onBack: () -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KarmikaColors.LightBackground)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 640.dp)
                .padding(
                    start = KarmikaDimensions.ScreenHorizontalPadding,
                    end = KarmikaDimensions.ScreenHorizontalPadding,
                    top = 8.dp,
                    bottom = 12.dp
                )
        ) {
            // Header (clean logo & title, no step count)
            OnboardingHeader()

            Spacer(modifier = Modifier.height(10.dp))

            OnboardingStepper(currentStep = 3)

            Spacer(modifier = Modifier.height(14.dp))

            // Main Scrollable Area with imePadding so text fields never hide behind keyboard
            BoxWithConstraints(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .imePadding()
            ) {
                val isWideScreen = maxWidth >= 520.dp

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Page Title & Subtitle
                    Text(
                        text = "Your Service Address",
                        style = KarmikaTypography.Heading2,
                        color = KarmikaColors.LightTextPrimary
                    )

                    Text(
                        text = "Where should our verified skilled workers provide service? This ensures prompt and accurate dispatch.",
                        style = KarmikaTypography.BodyMedium,
                        color = KarmikaColors.LightTextSecondary
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    // Street Address Input Field
                    ResponsiveAddressInputField(
                        label = "Street Address / House No.",
                        required = true,
                        value = uiState.streetAddress,
                        onValueChange = viewModel::onStreetAddressChanged,
                        placeholder = "e.g. House No. 42, Sector 17–A",
                        leadingIcon = Icons.Outlined.Home,
                        errorMessage = uiState.streetAddressError,
                        enabled = !uiState.isSubmitting,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        )
                    )

                    // Apartment / Landmark Input Field
                    ResponsiveAddressInputField(
                        label = "Apartment, Floor, Landmark (Optional)",
                        required = false,
                        value = uiState.apartment,
                        onValueChange = viewModel::onApartmentChanged,
                        placeholder = "e.g. Near City Garden, 2nd Floor",
                        leadingIcon = Icons.Outlined.LocationOn,
                        enabled = !uiState.isSubmitting,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        )
                    )

                    // City & State Fields (Adaptive 2-column on wider screens, stacked on small phones)
                    if (isWideScreen) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            ResponsiveAddressInputField(
                                label = "City",
                                required = true,
                                value = uiState.city,
                                onValueChange = viewModel::onCityChanged,
                                placeholder = "e.g. Chandigarh",
                                leadingIcon = Icons.Outlined.LocationCity,
                                errorMessage = uiState.cityError,
                                enabled = !uiState.isSubmitting,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Right) }
                                ),
                                modifier = Modifier.weight(1f)
                            )

                            ResponsiveAddressInputField(
                                label = "State / Region",
                                required = true,
                                value = uiState.state,
                                onValueChange = viewModel::onStateChanged,
                                placeholder = "e.g. Punjab",
                                leadingIcon = Icons.Outlined.Map,
                                errorMessage = uiState.stateError,
                                enabled = !uiState.isSubmitting,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    } else {
                        ResponsiveAddressInputField(
                            label = "City",
                            required = true,
                            value = uiState.city,
                            onValueChange = viewModel::onCityChanged,
                            placeholder = "e.g. Chandigarh",
                            leadingIcon = Icons.Outlined.LocationCity,
                            errorMessage = uiState.cityError,
                            enabled = !uiState.isSubmitting,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            )
                        )

                        ResponsiveAddressInputField(
                            label = "State / Region",
                            required = true,
                            value = uiState.state,
                            onValueChange = viewModel::onStateChanged,
                            placeholder = "e.g. Punjab",
                            leadingIcon = Icons.Outlined.Map,
                            errorMessage = uiState.stateError,
                            enabled = !uiState.isSubmitting,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            )
                        )
                    }

                    // Postal Code & Country Fields (Adaptive 2-column on wider screens, stacked on small phones)
                    if (isWideScreen) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            ResponsiveAddressInputField(
                                label = "Postal / PIN Code",
                                required = true,
                                value = uiState.postalCode,
                                onValueChange = viewModel::onPostalCodeChanged,
                                placeholder = "e.g. 160017",
                                leadingIcon = Icons.Outlined.PinDrop,
                                errorMessage = uiState.postalCodeError,
                                enabled = !uiState.isSubmitting,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Right) }
                                ),
                                modifier = Modifier.weight(1f)
                            )

                            ResponsiveAddressInputField(
                                label = "Country",
                                required = true,
                                value = uiState.country,
                                onValueChange = viewModel::onCountryChanged,
                                placeholder = "India",
                                leadingIcon = Icons.Outlined.Public,
                                enabled = !uiState.isSubmitting,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                        if (viewModel.validateAddress()) {
                                            onContinue()
                                        }
                                    }
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    } else {
                        ResponsiveAddressInputField(
                            label = "Postal / PIN Code",
                            required = true,
                            value = uiState.postalCode,
                            onValueChange = viewModel::onPostalCodeChanged,
                            placeholder = "e.g. 160017",
                            leadingIcon = Icons.Outlined.PinDrop,
                            errorMessage = uiState.postalCodeError,
                            enabled = !uiState.isSubmitting,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            )
                        )

                        ResponsiveAddressInputField(
                            label = "Country",
                            required = true,
                            value = uiState.country,
                            onValueChange = viewModel::onCountryChanged,
                            placeholder = "India",
                            leadingIcon = Icons.Outlined.Public,
                            enabled = !uiState.isSubmitting,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                    if (viewModel.validateAddress()) {
                                        onContinue()
                                    }
                                }
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Action Buttons inside the scroll view with extra clearance
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = onBack,
                            enabled = !uiState.isSubmitting,
                            shape = RoundedCornerShape(KarmikaDimensions.RadiusMedium),
                            border = BorderStroke(1.dp, KarmikaColors.Border),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = KarmikaColors.LightTextPrimary
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(KarmikaDimensions.ButtonHeight)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Back",
                                style = KarmikaTypography.Label.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                            )
                        }

                        Button(
                            onClick = {
                                focusManager.clearFocus()
                                if (viewModel.validateAddress()) {
                                    onContinue()
                                }
                            },
                            enabled = !uiState.isSubmitting,
                            shape = RoundedCornerShape(KarmikaDimensions.RadiusMedium),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = KarmikaColors.Primary,
                                contentColor = KarmikaColors.OnPrimary,
                                disabledContainerColor = KarmikaColors.Primary.copy(alpha = 0.6f)
                            ),
                            modifier = Modifier
                                .weight(1.5f)
                                .height(KarmikaDimensions.ButtonHeight),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
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
                                    style = KarmikaTypography.Label.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
                                    color = Color.White
                                )
                            } else {
                                Text(
                                    text = "Complete Setup",
                                    style = KarmikaTypography.Label.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold)
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

                    // Bottom padding at end of scroll
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

// Reusable Input Field with automatic BringIntoViewRequester on focus
@Composable
private fun ResponsiveAddressInputField(
    label: String,
    required: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    errorMessage: String? = null,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .bringIntoViewRequester(bringIntoViewRequester)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = KarmikaTypography.Label.copy(fontSize = 13.sp),
                color = KarmikaColors.LightTextPrimary
            )
            if (required) {
                Text(
                    text = " *",
                    style = KarmikaTypography.Label.copy(fontSize = 13.sp, fontWeight = FontWeight.Bold),
                    color = KarmikaColors.Error
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            delay(180)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            singleLine = true,
            isError = errorMessage != null,
            placeholder = {
                Text(
                    text = placeholder,
                    style = KarmikaTypography.BodyMedium.copy(fontSize = 13.sp),
                    color = KarmikaColors.LightTextSecondary.copy(alpha = 0.7f)
                )
            },
            leadingIcon = leadingIcon?.let { icon ->
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = if (errorMessage != null) KarmikaColors.Error else KarmikaColors.LightTextSecondary
                    )
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = RoundedCornerShape(KarmikaDimensions.RadiusMedium),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = KarmikaColors.Primary,
                unfocusedBorderColor = KarmikaColors.Border,
                errorBorderColor = KarmikaColors.Error,
                focusedContainerColor = KarmikaColors.LightSurface,
                unfocusedContainerColor = KarmikaColors.LightSurface,
                disabledContainerColor = KarmikaColors.LightSurfaceVariant,
                focusedTextColor = KarmikaColors.LightTextPrimary,
                unfocusedTextColor = KarmikaColors.LightTextPrimary
            )
        )

        AnimatedVisibility(
            visible = errorMessage != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = KarmikaColors.Error,
                    style = KarmikaTypography.BodySmall,
                    modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                )
            }
        }
    }
}
