package com.example.karmika.feature.auth.onboarding.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Engineering
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.core.common.model.UserRole
import com.example.karmika.core.ui.designsystem.KarmikaColors
import com.example.karmika.core.ui.designsystem.KarmikaDimensions
import com.example.karmika.core.ui.designsystem.KarmikaTypography
import com.example.karmika.feature.auth.onboarding.common.components.OnboardingHeader
import com.example.karmika.feature.auth.onboarding.common.components.OnboardingStepper

@Composable
fun OnboardingRoleScreen(
    onContinue: (UserRole) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedRole by remember { mutableStateOf(UserRole.HOUSEHOLD) }
    val scrollState = rememberScrollState()

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

            OnboardingStepper(currentStep = 1)

            Spacer(modifier = Modifier.height(14.dp))

            // Main Scrollable Area
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                // Page Title & Description
                Text(
                    text = "How will you use Karmika?",
                    style = KarmikaTypography.Heading2,
                    color = KarmikaColors.LightTextPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Choose the option that fits your goal best. Additional role capabilities can be unlocked later.",
                    style = KarmikaTypography.BodyMedium,
                    color = KarmikaColors.LightTextSecondary
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Role Options List
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Household / User Option
                    OnboardingRoleCard(
                        title = "Register as a User",
                        description = "Book verified local home repairs, housekeeping, plumbing, and daily assistance.",
                        icon = Icons.Outlined.Person,
                        badge = "INSTANT ACCESS",
                        badgeBgColor = KarmikaColors.PrimaryLight,
                        badgeTextColor = KarmikaColors.Primary,
                        selected = selectedRole == UserRole.HOUSEHOLD,
                        onClick = { selectedRole = UserRole.HOUSEHOLD }
                    )

                    // Worker Option
                    OnboardingRoleCard(
                        title = "Register as a Worker",
                        description = "Provide your skilled services (plumbing, carpentry, electrical) with fair wages.",
                        icon = Icons.Outlined.Engineering,
                        badge = "SERVICE PARTNER",
                        badgeBgColor = Color(0xFFFFF3E0),
                        badgeTextColor = Color(0xFFE65100),
                        selected = selectedRole == UserRole.WORKER,
                        onClick = { selectedRole = UserRole.WORKER }
                    )

                    // Company Option
                    OnboardingRoleCard(
                        title = "Register as a Company",
                        description = "Contract workforce solutions for facility management, commercial sites, and projects.",
                        icon = Icons.Outlined.Business,
                        badge = "ENTERPRISE",
                        badgeBgColor = Color(0xFFE8EAF6),
                        badgeTextColor = Color(0xFF283593),
                        selected = selectedRole == UserRole.COMPANY,
                        onClick = { selectedRole = UserRole.COMPANY }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Fixed Action Button
            Button(
                onClick = { onContinue(selectedRole) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(KarmikaDimensions.ButtonHeight),
                shape = RoundedCornerShape(KarmikaDimensions.RadiusMedium),
                colors = ButtonDefaults.buttonColors(
                    containerColor = KarmikaColors.Primary,
                    contentColor = KarmikaColors.OnPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = "Continue",
                    style = KarmikaTypography.Label.copy(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.width(KarmikaDimensions.SpaceSmall))
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

// =====================================================
// ROLE CARD COMPONENT
// =====================================================

@Composable
private fun OnboardingRoleCard(
    title: String,
    description: String,
    icon: ImageVector,
    badge: String,
    badgeBgColor: Color,
    badgeTextColor: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    val borderColor by animateColorAsState(
        targetValue = if (selected) KarmikaColors.Primary else KarmikaColors.Border,
        label = "BorderColorAnimation"
    )
    val borderWidth by animateDpAsState(
        targetValue = if (selected) 2.dp else 1.dp,
        label = "BorderWidthAnimation"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(KarmikaDimensions.RadiusLarge))
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(KarmikaDimensions.RadiusLarge)
            )
            .clickable { onClick() },
        color = KarmikaColors.LightSurface, // Constant background - does not change on select
        shape = RoundedCornerShape(KarmikaDimensions.RadiusLarge),
        shadowElevation = if (selected) 2.dp else 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon Header
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(KarmikaDimensions.RadiusMedium))
                        .background(badgeBgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = badgeTextColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Status Badge
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(badgeBgColor)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = badge,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = badgeTextColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                style = KarmikaTypography.Heading3.copy(fontSize = 17.sp),
                color = KarmikaColors.LightTextPrimary
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = description,
                style = KarmikaTypography.BodyMedium.copy(fontSize = 13.sp),
                color = KarmikaColors.LightTextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Selection Radio Row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                RadioButton(
                    selected = selected,
                    onClick = onClick,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = KarmikaColors.Primary,
                        unselectedColor = KarmikaColors.LightTextSecondary
                    ),
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(KarmikaDimensions.SpaceSmall))

                Text(
                    text = if (selected) "Selected" else "Select Option",
                    style = KarmikaTypography.Label.copy(
                        fontSize = 13.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                    ),
                    color = if (selected) KarmikaColors.Primary else KarmikaColors.LightTextSecondary
                )
            }
        }
    }
}
