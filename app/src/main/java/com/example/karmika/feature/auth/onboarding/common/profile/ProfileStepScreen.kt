package com.example.karmika.feature.auth.onboarding.common.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material.icons.outlined.FlashOn
import androidx.compose.material.icons.outlined.Opacity
import androidx.compose.material.icons.outlined.Upload
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.karmika.core.ui.designsystem.KarmikaColors
import com.example.karmika.core.ui.designsystem.KarmikaDimensions
import com.example.karmika.core.ui.designsystem.KarmikaTypography
import com.example.karmika.feature.auth.onboarding.common.components.OnboardingStepper

data class ColorThemeOption(
    val id: Int,
    val icon: ImageVector,
    val backgroundBrush: Brush,
    val iconTint: Color
)

private val ColorThemeOptions = listOf(
    ColorThemeOption(
        id = 0,
        icon = Icons.Outlined.Eco,
        backgroundBrush = Brush.linearGradient(listOf(Color(0xFF006B4F), Color(0xFF00966D))),
        iconTint = Color(0xFFE8F6F1)
    ),
    ColorThemeOption(
        id = 1,
        icon = Icons.Outlined.WaterDrop,
        backgroundBrush = Brush.linearGradient(listOf(Color(0xFF2979FF), Color(0xFF64B5F6))),
        iconTint = Color.White
    ),
    ColorThemeOption(
        id = 2,
        icon = Icons.Outlined.FlashOn,
        backgroundBrush = Brush.linearGradient(listOf(Color(0xFFFF8F00), Color(0xFFFFB300))),
        iconTint = Color.White
    ),
    ColorThemeOption(
        id = 3,
        icon = Icons.Outlined.Check,
        backgroundBrush = Brush.linearGradient(listOf(Color(0xFF7B1FA2), Color(0xFFAB47BC))),
        iconTint = Color.White
    )
)

@Composable
fun ProfileStepScreen(
    viewModel: ProfileStepViewModel = viewModel(),
    onBack: () -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    // Image file picker launcher
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.onPhotoSelected(uri)
        }
    }

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
                    top = 10.dp,
                    bottom = 12.dp
                )
        ) {
            // =====================================================
            // TOP BAR: Brand Title & Close Button (as shown in screenshot)
            // =====================================================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Karmika Logo Icon
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(KarmikaColors.PrimaryLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Eco,
                            contentDescription = "Logo",
                            tint = KarmikaColors.Primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Karmika Account Setup",
                        style = KarmikaTypography.Heading3.copy(fontSize = 19.sp),
                        fontWeight = FontWeight.Bold,
                        color = KarmikaColors.LightTextPrimary
                    )
                }

                IconButton(
                    onClick = onBack,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = KarmikaColors.LightTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Stepper: 1 Completed -> 2 Profile -> 3 Address
            OnboardingStepper(currentStep = 2)

            Spacer(modifier = Modifier.height(18.dp))

            // =====================================================
            // SCROLLABLE CONTENT BODY
            // =====================================================
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                // Section Title (Exact match to screenshot)
                Text(
                    text = "Confirm your personal details and customize your appearance.",
                    style = KarmikaTypography.Heading3.copy(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 22.sp
                    ),
                    color = KarmikaColors.LightTextPrimary
                )

                Spacer(modifier = Modifier.height(14.dp))

                // =====================================================
                // USER DETAILS CARD (populated from register/login)
                // =====================================================
                val activeTheme = ColorThemeOptions.getOrElse(uiState.selectedAvatar) {
                    ColorThemeOptions.first()
                }

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = KarmikaColors.LightSurface,
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shadowElevation = 0.5.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Avatar Circle with initial letter
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(activeTheme.backgroundBrush),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = uiState.fullName.firstOrNull()?.uppercase() ?: "U",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = uiState.fullName.ifBlank { "User" },
                                    style = KarmikaTypography.Heading3.copy(fontSize = 17.sp),
                                    fontWeight = FontWeight.Bold,
                                    color = KarmikaColors.LightTextPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                // "Pending Setup" status pill
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFFEF3C7))
                                        .padding(horizontal = 8.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Pending Setup",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFFB45309)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            // Email row
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.Email,
                                    contentDescription = null,
                                    modifier = Modifier.size(13.dp),
                                    tint = Color(0xFF6366F1)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = uiState.email.ifBlank { "No email registered" },
                                    style = KarmikaTypography.BodyMedium.copy(fontSize = 13.sp),
                                    color = KarmikaColors.LightTextPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            // Phone row (if present, or default placeholder)
                            if (uiState.phone.isNotBlank()) {
                                Spacer(modifier = Modifier.height(3.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.CreditCard,
                                        contentDescription = null,
                                        modifier = Modifier.size(13.dp),
                                        tint = Color(0xFF8B5CF6)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = uiState.phone,
                                        style = KarmikaTypography.BodyMedium.copy(fontSize = 13.sp),
                                        color = KarmikaColors.LightTextPrimary
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // =====================================================
                // UPLOAD PROFILE PICTURE (MANDATORY) *
                // =====================================================
                Text(
                    text = "Upload Profile Picture (Mandatory) *",
                    style = KarmikaTypography.Heading3.copy(fontSize = 16.sp),
                    fontWeight = FontWeight.Bold,
                    color = KarmikaColors.LightTextPrimary
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Button 1: Upload Photo File
                Button(
                    onClick = { photoPickerLauncher.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = KarmikaColors.Primary,
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Upload,
                        contentDescription = "Upload",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (uiState.selectedPhotoUri != null) "Photo Selected ✓" else "Upload Photo File",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Button 2: Take Live Selfie
                Button(
                    onClick = { photoPickerLauncher.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = KarmikaColors.Primary,
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CameraAlt,
                        contentDescription = "Take Selfie",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Take Live Selfie",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Supports JPG, PNG, WEBP — facial vector extracted automatically on client and verified in backend.",
                    style = KarmikaTypography.BodySmall.copy(fontSize = 12.sp, lineHeight = 16.sp),
                    color = KarmikaColors.LightTextSecondary
                )

                Spacer(modifier = Modifier.height(18.dp))

                // =====================================================
                // OR CHOOSE COLOR THEME
                // =====================================================
                Text(
                    text = "Or Choose Color Theme",
                    style = KarmikaTypography.Heading3.copy(fontSize = 16.sp),
                    fontWeight = FontWeight.Bold,
                    color = KarmikaColors.LightTextPrimary
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    ColorThemeOptions.forEach { theme ->
                        val isSelected = uiState.selectedAvatar == theme.id

                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(theme.backgroundBrush)
                                .border(
                                    width = if (isSelected) 3.dp else 0.dp,
                                    color = if (isSelected) KarmikaColors.Primary else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable { viewModel.onAvatarSelected(theme.id) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = theme.icon,
                                contentDescription = null,
                                tint = theme.iconTint,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // =====================================================
                // OR ENTER IMAGE URL (OPTIONAL)
                // =====================================================
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF8FAFC),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "Or Enter Image URL (Optional)",
                            style = KarmikaTypography.Label.copy(fontSize = 14.sp),
                            fontWeight = FontWeight.SemiBold,
                            color = KarmikaColors.LightTextPrimary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = uiState.profileImageUrl,
                            onValueChange = viewModel::onProfileImageUrlChanged,
                            placeholder = {
                                Text(
                                    text = "https://example.com/avatar.jpg",
                                    fontSize = 14.sp,
                                    color = Color(0xFF94A3B8)
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Uri,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() }
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = KarmikaColors.Primary,
                                unfocusedBorderColor = Color(0xFFCBD5E1),
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Leave empty to use your default colored avatar.",
                            style = KarmikaTypography.BodySmall.copy(fontSize = 12.sp),
                            color = KarmikaColors.LightTextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // =====================================================
            // BOTTOM ACTION BUTTONS: Back & Continue
            // =====================================================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onBack,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color(0xFFCBD5E1)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = KarmikaColors.LightTextPrimary
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Back",
                        style = KarmikaTypography.Label.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    )
                }

                Button(
                    onClick = onContinue,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = KarmikaColors.Primary,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1.5f)
                        .height(48.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp)
                ) {
                    Text(
                        text = "Continue",
                        style = KarmikaTypography.Label.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Continue",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
