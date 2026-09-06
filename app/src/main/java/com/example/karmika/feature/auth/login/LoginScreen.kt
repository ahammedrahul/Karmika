package com.example.karmika.feature.auth.login
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.karmika.core.ui.components.KarmikaButton

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {}
) {

    val state by viewModel.uiState
        .collectAsStateWithLifecycle()

    LaunchedEffect(state.loginSuccessful) {

        if (state.loginSuccessful) {

            onLoginSuccess()
        }
    }

    LoginContent(
        state = state,

        onEmailChanged =
            viewModel::onEmailChanged,

        onPasswordChanged =
            viewModel::onPasswordChanged,

        onLoginClick =
            viewModel::login,

        onRegisterClick =
            onRegisterClick,

        onForgotPasswordClick =
            onForgotPasswordClick
    )
}


@Composable
private fun LoginContent(
    state: LoginUiState,

    onEmailChanged: (String) -> Unit,

    onPasswordChanged: (String) -> Unit,

    onLoginClick: () -> Unit,

    onRegisterClick: () -> Unit,

    onForgotPasswordClick: () -> Unit
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {


            // =====================================================
            // TITLE
            // =====================================================

            Text(
                text = "Welcome to Karmika",

                modifier =
                    Modifier.fillMaxWidth(),

                textAlign =
                    TextAlign.Center,

                fontSize = 24.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    MaterialTheme.colorScheme.onBackground
            )


            Spacer(
                modifier = Modifier.height(14.dp)
            )


            Text(
                text =
                    "Sign in to continue or create a new account",

                modifier =
                    Modifier.fillMaxWidth(),

                textAlign =
                    TextAlign.Center,

                fontSize = 14.sp,

                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )


            Spacer(
                modifier = Modifier.height(26.dp)
            )


            // =====================================================
            // LOGIN / SIGN UP
            // =====================================================

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(4.dp),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                // LOGIN SELECTED

                KarmikaButton(
                    text = "Login",

                    onClick = {},

                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()

                )


                // SIGN UP

                OutlinedButton(
                    onClick =
                        onRegisterClick,

                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),

                    shape =
                        RoundedCornerShape(10.dp),

                    contentPadding =
                        PaddingValues(0.dp)
                ) {

                    Text(
                        text = "Sign Up",

                        fontSize = 16.sp,

                        fontWeight =
                            FontWeight.Medium
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(26.dp)
            )


            // =====================================================
            // EMAIL
            // =====================================================

            OutlinedTextField(
                value = state.email,

                onValueChange =
                    onEmailChanged,

                modifier =
                    Modifier.fillMaxWidth(),

                placeholder = {
                    Text(
                        text =
                            "Enter your email address"
                    )
                },

                leadingIcon = {
                    Icon(
                        imageVector =
                            Icons.Default.Email,

                        contentDescription =
                            "Email"
                    )
                },

                singleLine = true,

                shape =
                    RoundedCornerShape(12.dp),

                isError =
                    state.emailError != null
            )


            if (state.emailError != null) {

                Text(
                    text =
                        state.emailError!!,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 4.dp
                        ),

                    fontSize = 12.sp,

                    color =
                        MaterialTheme.colorScheme.error
                )
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // =====================================================
            // PASSWORD
            // =====================================================

            OutlinedTextField(
                value =
                    state.password,

                onValueChange =
                    onPasswordChanged,

                modifier =
                    Modifier.fillMaxWidth(),

                placeholder = {
                    Text(
                        text =
                            "Enter your password"
                    )
                },

                leadingIcon = {
                    Icon(
                        imageVector =
                            Icons.Default.Lock,

                        contentDescription =
                            "Password"
                    )
                },

                trailingIcon = {

                    IconButton(
                        onClick = {
                            passwordVisible =
                                !passwordVisible
                        }
                    ) {

                        Icon(
                            imageVector =
                                if (passwordVisible)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.Visibility,

                            contentDescription =
                                if (passwordVisible)
                                    "Hide password"
                                else
                                    "Show password"
                        )
                    }
                },

                visualTransformation =
                    if (passwordVisible)
                        androidx.compose.ui.text.input
                            .VisualTransformation.None
                    else
                        androidx.compose.ui.text.input
                            .PasswordVisualTransformation(),

                singleLine = true,

                shape =
                    RoundedCornerShape(12.dp),

                isError =
                    state.passwordError != null
            )


            if (state.passwordError != null) {

                Text(
                    text =
                        state.passwordError!!,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 4.dp
                        ),

                    fontSize = 12.sp,

                    color =
                        MaterialTheme.colorScheme.error
                )
            }


            // =====================================================
            // FORGOT PASSWORD
            // =====================================================

            TextButton(
                onClick =
                    onForgotPasswordClick,

                modifier =
                    Modifier.align(
                        Alignment.End
                    ),

                contentPadding =
                    PaddingValues(
                        horizontal = 0.dp,
                        vertical = 2.dp
                    )
            ) {

                Text(
                    text =
                        "Forgot Password?",

                    fontSize = 14.sp,

                    fontWeight =
                        FontWeight.Medium,

                    color =
                        MaterialTheme.colorScheme.primary
                )
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // =====================================================
            // LOGIN BUTTON
            // =====================================================

            KarmikaButton(
                text = "Login  →",

                onClick =
                    onLoginClick,

                modifier =
                    Modifier.fillMaxWidth(),

                isLoading =
                    state.isLoading
            )


            Spacer(
                modifier = Modifier.height(22.dp)
            )


            // =====================================================
            // OR
            // =====================================================

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                HorizontalDivider(
                    modifier =
                        Modifier.weight(1f)
                )

                Text(
                    text = "  OR  ",

                    fontSize = 12.sp,

                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                )

                HorizontalDivider(
                    modifier =
                        Modifier.weight(1f)
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // =====================================================
            // GOOGLE
            // =====================================================

            OutlinedButton(
                onClick = {},

                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),

                shape =
                    RoundedCornerShape(10.dp),

                contentPadding =
                    PaddingValues(0.dp)
            ) {

                Text(
                    text =
                        "Continue with Google",

                    fontSize = 15.sp,

                    fontWeight =
                        FontWeight.Medium
                )
            }


            Spacer(
                modifier = Modifier.height(10.dp)
            )


            // =====================================================
            // APPLE
            // =====================================================

            OutlinedButton(
                onClick = {},

                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),

                shape =
                    RoundedCornerShape(10.dp),

                contentPadding =
                    PaddingValues(0.dp)
            ) {

                Text(
                    text =
                        "Continue with Apple",

                    fontSize = 15.sp,

                    fontWeight =
                        FontWeight.Medium
                )
            }


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            // =====================================================
            // REGISTER
            // =====================================================

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.Center,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text =
                        "Don't have an account? ",

                    fontSize = 13.sp,

                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                )

                TextButton(
                    onClick =
                        onRegisterClick,

                    contentPadding =
                        PaddingValues(
                            horizontal = 2.dp,
                            vertical = 0.dp
                        )
                ) {

                    Text(
                        text = "Sign Up",

                        fontSize = 13.sp,

                        fontWeight =
                            FontWeight.SemiBold,

                        color =
                            MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}