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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
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

@Composable
fun OnboardingRoleScreen(
    onContinue: (UserRole) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedRole by remember { mutableStateOf(UserRole.HOUSEHOLD) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KarmikaColors.LightBackground)
            .padding(
                horizontal = KarmikaDimensions.ScreenHorizontalPadding,
                vertical = KarmikaDimensions.ScreenVerticalPadding
            )
    ) {
        // Main Scrollable Area
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceLarge))

            // -----------------------------------------
            // HEADER & STEPPER
            // -----------------------------------------
            Text(
                text = "Karmika Account Setup",
                style = KarmikaTypography.Heading3,
                color = KarmikaColors.LightTextPrimary
            )

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceLarge))

            OnboardingStepper(currentStep = 1)

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceXXLarge))

            // -----------------------------------------
            // PAGE TITLE & DESCRIPTION
            // -----------------------------------------
            Text(
                text = "How will you use Karmika?",
                style = KarmikaTypography.Heading2,
                color = KarmikaColors.LightTextPrimary
            )

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceSmall))

            Text(
                text = "Choose the option that fits your goal best. Additional role capabilities can be unlocked later.",
                style = KarmikaTypography.BodyLarge,
                color = KarmikaColors.LightTextSecondary
            )

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceExtraLarge))

            // -----------------------------------------
            // ROLE OPTIONS
            // -----------------------------------------
            Column(
                verticalArrangement = Arrangement.spacedBy(KarmikaDimensions.SpaceMedium)
            ) {
                // Household Option
                OnboardingRoleCard(
                    title = "Register as a User",
                    description = "Book verified local home repairs, housekeeping, plumbing, and daily assistance.",
                    icon = Icons.Outlined.Person,
                    badge = "INSTANT ACCESS",
                    badgeBgColor = KarmikaColors.Primary.copy(alpha = 0.12f),
                    badgeTextColor = KarmikaColors.Primary,
                    selected = selectedRole == UserRole.HOUSEHOLD,
                    onClick = { selectedRole = UserRole.HOUSEHOLD }
                )

                // Worker Option
                OnboardingRoleCard(
                    title = "Register as a Worker",
                    description = "Provide your skilled services (plumbing, carpentry, electrical) with fair wages.",
                    icon = Icons.Outlined.Engineering,
                    badge = "COMING SOON",
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

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceLarge))
        }

        // -----------------------------------------
        // FIXED ACTION BUTTON
        // -----------------------------------------
        Button(
            onClick = { onContinue(selectedRole) },
            modifier = Modifier
                .fillMaxWidth()
                .height(KarmikaDimensions.ButtonHeight),
            shape = RoundedCornerShape(KarmikaDimensions.RadiusMedium),
            colors = ButtonDefaults.buttonColors(
                containerColor = KarmikaColors.Primary,
                contentColor = KarmikaColors.OnPrimary
            )
        ) {
            Text(
                text = "Continue",
                style = KarmikaTypography.Label.copy(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.width(KarmikaDimensions.SpaceSmall))
            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// =====================================================
// STEPPER COMPONENT
// =====================================================

@Composable
private fun OnboardingStepper(currentStep: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom, // Aligns circles with the connecting line
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Step 1
        StepItem(
            number = "1",
            label = "Select Role",
            isActive = currentStep >= 1,
            modifier = Modifier.weight(1f)
        )

        StepLine(
            isActive = currentStep > 1,
            modifier = Modifier.padding(bottom = 13.dp) // Aligns line to middle of 28.dp circles
        )

        // Step 2
        StepItem(
            number = "2",
            label = "Profile",
            isActive = currentStep >= 2,
            modifier = Modifier.weight(1f)
        )

        StepLine(
            isActive = currentStep > 2,
            modifier = Modifier.padding(bottom = 13.dp)
        )

        // Step 3
        StepItem(
            number = "3",
            label = "Address",
            isActive = currentStep >= 3,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun StepItem(
    number: String,
    label: String,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            style = KarmikaTypography.Label,
            color = if (isActive) KarmikaColors.LightTextPrimary else KarmikaColors.LightTextSecondary,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(if (isActive) KarmikaColors.Primary else KarmikaColors.LightSurfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = number,
                color = if (isActive) KarmikaColors.OnPrimary else KarmikaColors.LightTextSecondary,
                style = KarmikaTypography.Label
            )
        }
    }
}

@Composable
private fun StepLine(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(32.dp)
            .height(2.dp)
            .background(if (isActive) KarmikaColors.Primary else KarmikaColors.Border)
    )
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
    val containerColor by animateColorAsState(
        targetValue = if (selected) KarmikaColors.PrimaryLight.copy(alpha = 0.3f) else KarmikaColors.LightSurface,
        label = "ContainerColorAnimation"
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
        color = containerColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(KarmikaDimensions.SpaceLarge)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon Header
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(KarmikaDimensions.RadiusMedium))
                        .background(badgeBgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = badgeTextColor,
                        modifier = Modifier.size(22.dp)
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

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceMedium))

            Text(
                text = title,
                style = KarmikaTypography.Heading3,
                color = KarmikaColors.LightTextPrimary
            )

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceSmall))

            Text(
                text = description,
                style = KarmikaTypography.BodyMedium,
                color = KarmikaColors.LightTextSecondary
            )

            Spacer(modifier = Modifier.height(KarmikaDimensions.SpaceMedium))

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
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 4.dp)
                )

                Spacer(modifier = Modifier.width(KarmikaDimensions.SpaceSmall))

                Text(
                    text = if (selected) "Selected" else "Select Option",
                    style = KarmikaTypography.Label,
                    color = if (selected) KarmikaColors.Primary else KarmikaColors.LightTextSecondary
                )
            }
        }
    }
}