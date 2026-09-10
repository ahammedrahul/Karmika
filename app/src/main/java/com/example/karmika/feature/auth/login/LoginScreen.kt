package com.example.karmika.feature.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.karmika.R
import com.example.karmika.core.ui.components.KarmikaButton
import com.example.karmika.core.ui.designsystem.KarmikaColors
import com.example.karmika.core.ui.designsystem.KarmikaDimensions
import com.example.karmika.core.ui.designsystem.KarmikaTypography

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onBackToHomeClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state.loginSuccessful) {
        if (state.loginSuccessful) {
            onLoginSuccess()
        }
    }

    LoginContent(
        state = state,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onLoginClick = viewModel::login,
        onRegisterClick = onRegisterClick,
        onForgotPasswordClick = onForgotPasswordClick,
        onBackToHomeClick = onBackToHomeClick
    )
}

@Composable
private fun LoginContent(
    state: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onBackToHomeClick: () -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KarmikaColors.LightSurface)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = KarmikaDimensions.ScreenHorizontalPadding,
                    vertical = KarmikaDimensions.ScreenVerticalPadding
                )
        ) {
            // Space above Top Bar
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
                        style = KarmikaTypography.Heading2
                    )
                }

                // SIGN UP BUTTON (Flat container with no material ripple or shadow)
                Row(
                    modifier = Modifier
                        .background(
                            color = KarmikaColors.LightSurfaceVariant,
                            shape = RoundedCornerShape(KarmikaDimensions.RadiusExtraLarge)
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null, // Suppresses hover and press overlay effects
                            onClick = onRegisterClick
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Sign Up",
                        tint = KarmikaColors.Primary
                    )

                    Spacer(modifier = Modifier.width(KarmikaDimensions.SpaceSmall))

                    Text(
                        text = "Sign Up",
                        color = KarmikaColors.Primary,
                        style = KarmikaTypography.BodyMedium
                    )
                }
            }

            // =================================================
            // SPACE BEFORE TITLE
            // =================================================
            Spacer(modifier = Modifier.height(48.dp))

            // =================================================
            // TITLE
            // =================================================
            Text(
                text = "Sign In",
                color = KarmikaColors.LightTextPrimary,
                style = KarmikaTypography.Heading1
            )

            Spacer(modifier = Modifier.height(24.dp))

            // =================================================
            // EMAIL / USERNAME
            // =================================================
            OutlinedTextField(
                value = state.email,
                onValueChange = onEmailChanged,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Email or Username",
                        color = KarmikaColors.LightTextSecondary
                    )
                },
                singleLine = true,
                isError = state.emailError != null,
                supportingText = {
                    state.emailError?.let {
                        Text(
                            text = it,
                            color = KarmikaColors.Error
                        )
                    }
                },
                shape = RoundedCornerShape(KarmikaDimensions.RadiusExtraLarge)
            )

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceMedium))

            // =================================================
            // PASSWORD
            // =================================================
            OutlinedTextField(
                value = state.password,
                onValueChange = onPasswordChanged,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Password",
                        color = KarmikaColors.LightTextSecondary
                    )
                },
                singleLine = true,
                isError = state.passwordError != null,
                supportingText = {
                    state.passwordError?.let {
                        Text(
                            text = it,
                            color = KarmikaColors.Error
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Icon(
                            imageVector = if (passwordVisible) {
                                Icons.Outlined.VisibilityOff
                            } else {
                                Icons.Outlined.Visibility
                            },
                            contentDescription = if (passwordVisible) {
                                "Hide password"
                            } else {
                                "Show password"
                            },
                            tint = KarmikaColors.LightTextSecondary
                        )
                    }
                },
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                shape = RoundedCornerShape(KarmikaDimensions.RadiusExtraLarge)
            )

            // =================================================
            // FORGOT PASSWORD
            // =================================================
            TextButton(
                onClick = onForgotPasswordClick,
                modifier = Modifier.align(Alignment.Start),
                contentPadding = PaddingValues(
                    horizontal = 8.dp,
                    vertical = 2.dp
                )
            ) {
                Text(
                    text = "Forgot password?",
                    color = KarmikaColors.Primary,
                    style = KarmikaTypography.BodyMedium
                )
            }

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceSmall))

            // =================================================
            // SIGN IN BUTTON
            // =================================================
            KarmikaButton(
                text = "Sign In",
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth(),
                isLoading = state.isLoading
            )

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceLarge))
        }
    }
}