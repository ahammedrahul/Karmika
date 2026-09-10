package com.example.karmika.feature.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.karmika.R
import com.example.karmika.core.ui.components.KarmikaButton
import com.example.karmika.core.ui.components.KarmikaPasswordField
import com.example.karmika.core.ui.components.KarmikaTextField
import com.example.karmika.core.ui.designsystem.KarmikaColors
import com.example.karmika.core.ui.designsystem.KarmikaDimensions
import com.example.karmika.core.ui.designsystem.KarmikaTypography

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onLoginClick: () -> Unit,
    onRegistrationSuccess: () -> Unit,
    onBackToHomeClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    // =====================================================
    // REGISTRATION SUCCESS
    // =====================================================
    LaunchedEffect(uiState.registrationSuccessful) {
        if (uiState.registrationSuccessful) {
            viewModel.clearRegistrationSuccess()
            onRegistrationSuccess()
        }
    }

    // =====================================================
    // SCREEN
    // =====================================================
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KarmikaColors.LightSurface)
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = KarmikaDimensions.ScreenHorizontalPadding,
                vertical = KarmikaDimensions.ScreenVerticalPadding
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Space above the Top Bar
        Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceMedium))

        // =================================================
        // TOP BAR
        // =================================================
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // LOGO + NAME
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Karmika Logo",
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(KarmikaDimensions.SpaceSmall))

                Text(
                    text = "Karmika",
                    color = KarmikaColors.LightTextPrimary,
                    style = KarmikaTypography.Heading3
                )
            }

            // SIGN IN BUTTON (Flat custom Clickable without default material shadow/hover effect)
            Row(
                modifier = Modifier
                    .background(
                        color = KarmikaColors.LightSurfaceVariant,
                        shape = RoundedCornerShape(KarmikaDimensions.RadiusExtraLarge)
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null, // Removes ripple / press effect
                        onClick = onLoginClick
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Login,
                    contentDescription = "Sign In",
                    tint = KarmikaColors.Primary
                )

                Spacer(modifier = Modifier.width(KarmikaDimensions.SpaceSmall))

                Text(
                    text = "Sign In",
                    color = KarmikaColors.Primary,
                    style = KarmikaTypography.BodyMedium
                )
            }
        }

        // =================================================
        // TITLE
        // =================================================
        Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceLarge))

        Text(
            text = "Sign Up",
            modifier = Modifier.fillMaxWidth(),
            color = KarmikaColors.LightTextPrimary,
            style = KarmikaTypography.Heading1
        )

        Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceExtraLarge))

        // =================================================
        // FULL NAME
        // =================================================
        KarmikaTextField(
            value = uiState.fullName,
            onValueChange = viewModel::onFullNameChanged,
            label = "Full Name",
            placeholder = "Full Name",
            leadingIcon = Icons.Outlined.Person,
            error = uiState.fullNameError
        )

        RegisterErrorText(error = uiState.fullNameError)

        Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceMedium))

        // =================================================
        // EMAIL
        // =================================================
        KarmikaTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChanged,
            label = "Email Address",
            placeholder = "Email Address",
            leadingIcon = Icons.Outlined.Email,
            error = uiState.emailError
        )

        RegisterErrorText(error = uiState.emailError)

        Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceMedium))

        // =================================================
        // PHONE
        // =================================================
        KarmikaTextField(
            value = uiState.phone,
            onValueChange = { value ->
                if (value.length <= 10 && value.all { it.isDigit() }) {
                    viewModel.onPhoneChanged(value)
                }
            },
            label = "Phone Number",
            placeholder = "Phone Number (e.g. 9876543210)",
            leadingIcon = Icons.Outlined.Phone,
            error = uiState.phoneError
        )

        RegisterErrorText(error = uiState.phoneError)

        Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceMedium))

        // =================================================
        // PASSWORD
        // =================================================
        KarmikaPasswordField(
            value = uiState.password,
            onValueChange = viewModel::onPasswordChanged,
            label = "Password",
            placeholder = "Password",
            error = uiState.passwordError
        )

        RegisterErrorText(error = uiState.passwordError)

        Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceMedium))

        // =================================================
        // CONFIRM PASSWORD
        // =================================================
        KarmikaPasswordField(
            value = uiState.confirmPassword,
            onValueChange = viewModel::onConfirmPasswordChanged,
            label = "Confirm Password",
            placeholder = "Confirm Password",
            error = uiState.confirmPasswordError
        )

        RegisterErrorText(error = uiState.confirmPasswordError)

        // =================================================
        // SIGN UP BUTTON
        // =================================================
        Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceLarge))

        KarmikaButton(
            text = "Sign Up",
            onClick = { viewModel.register() },
            modifier = Modifier.fillMaxWidth() // Enforces full width
        )
    }
}

// =========================================================
// ERROR TEXT
// =========================================================
@Composable
private fun RegisterErrorText(
    error: String?
) {
    if (error != null) {
        Text(
            text = error,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = KarmikaDimensions.SpaceLarge,
                    top = KarmikaDimensions.SpaceExtraSmall
                ),
            color = KarmikaColors.Error,
            style = KarmikaTypography.BodySmall
        )
    }
}