package com.example.karmika.feature.auth.register

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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.karmika.core.common.model.UserRole
import com.example.karmika.core.ui.components.KarmikaButton
import com.example.karmika.core.ui.components.KarmikaPasswordField
import com.example.karmika.core.ui.components.KarmikaTextField

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onLoginClick: () -> Unit = {},
    onRegistrationSuccess: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state.registrationSuccessful) {
        if (state.registrationSuccessful) {
            onRegistrationSuccess()
            viewModel.clearRegistrationSuccess()
        }
    }

    RegisterContent(
        state = state,
        onFullNameChanged = viewModel::onFullNameChanged,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onConfirmPasswordChanged = viewModel::onConfirmPasswordChanged,
        onRoleSelected = viewModel::onRoleSelected,
        onRegisterClick = viewModel::register,
        onLoginClick = onLoginClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegisterContent(
    state: RegisterUiState,
    onFullNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onRoleSelected: (UserRole) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // =====================================================
            // TITLE
            // =====================================================

            Text(
                text = "Welcome to Karmika",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Create your account to get started",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(22.dp))

            // =====================================================
            // LOGIN / SIGN UP SELECTOR
            // =====================================================

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(9.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Login",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                KarmikaButton(
                    text = "Sign Up",
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // =====================================================
            // FULL NAME
            // =====================================================

            KarmikaTextField(
                value = state.fullName,
                onValueChange = onFullNameChanged,
                label = "Full name",
                placeholder = "Enter your full name",
                error = state.fullNameError
            )

            Spacer(modifier = Modifier.height(10.dp))

            // =====================================================
            // ROLE
            // =====================================================

            Text(
                text = "I want to join as:",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(6.dp))

            RoleDropdown(
                selectedRole = state.selectedRole,
                onRoleSelected = onRoleSelected
            )

            Spacer(modifier = Modifier.height(10.dp))

            // =====================================================
            // EMAIL
            // =====================================================

            KarmikaTextField(
                value = state.email,
                onValueChange = onEmailChanged,
                label = "Email",
                placeholder = "Enter your email address",
                error = state.emailError
            )

            Spacer(modifier = Modifier.height(10.dp))

            // =====================================================
            // PASSWORD
            // =====================================================

            KarmikaPasswordField(
                value = state.password,
                onValueChange = onPasswordChanged,
                label = "Password",
                placeholder = "Enter your password",
                error = state.passwordError
            )

            Spacer(modifier = Modifier.height(10.dp))

            // =====================================================
            // CONFIRM PASSWORD
            // =====================================================

            KarmikaPasswordField(
                value = state.confirmPassword,
                onValueChange = onConfirmPasswordChanged,
                label = "Confirm password",
                placeholder = "Confirm your password",
                error = state.confirmPasswordError
            )

            Spacer(modifier = Modifier.height(18.dp))

            // =====================================================
            // SIGN UP BUTTON
            // =====================================================

            KarmikaButton(
                text = "Sign Up  →",
                onClick = onRegisterClick,
                modifier = Modifier.fillMaxWidth(),
                isLoading = state.isLoading
            )

            Spacer(modifier = Modifier.height(18.dp))

            // =====================================================
            // OR
            // =====================================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "  OR  ",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // =====================================================
            // GOOGLE
            // =====================================================

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Continue with Google",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // =====================================================
            // APPLE
            // =====================================================

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Continue with Apple",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // =====================================================
            // ALREADY HAVE ACCOUNT
            // =====================================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                TextButton(
                    onClick = onLoginClick,
                    contentPadding = PaddingValues(
                        horizontal = 2.dp,
                        vertical = 0.dp
                    )
                ) {
                    Text(
                        text = "Login",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// =============================================================
// ROLE DROPDOWN
// =============================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RoleDropdown(
    selectedRole: UserRole,
    onRoleSelected: (UserRole) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val selectedText = when (selectedRole) {
        UserRole.HOUSEHOLD -> "Household"
        UserRole.WORKER -> "Worker / Pro"
        UserRole.COMPANY -> "Company"
        UserRole.ORGANIZATION -> "Organization"
    }

    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFDFF8EC),
            Color(0xFFECFFF5)
        )
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(gradient)
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                shape = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                }
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,

            onDismissRequest = {
                expanded = false
            },

            modifier = Modifier.exposedDropdownSize(),

            shape = RoundedCornerShape(14.dp),

            containerColor = Color(0xFFF5F6F5),

            tonalElevation = 3.dp,

            shadowElevation = 6.dp
        ) {

            DropdownMenuItem(
                text = {
                    Text("Household")
                },
                onClick = {
                    onRoleSelected(UserRole.HOUSEHOLD)
                    expanded = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text("Worker / Pro")
                },
                onClick = {
                    onRoleSelected(UserRole.WORKER)
                    expanded = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text("Company")
                },
                onClick = {
                    onRoleSelected(UserRole.COMPANY)
                    expanded = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text("Organization")
                },
                onClick = {
                    onRoleSelected(UserRole.ORGANIZATION)
                    expanded = false
                }
            )
        }
    }
}
