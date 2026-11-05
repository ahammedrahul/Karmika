package com.example.karmika.feature.auth.onboarding.common.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.core.ui.designsystem.KarmikaColors
import com.example.karmika.core.ui.designsystem.KarmikaTypography

@Composable
fun OnboardingStepper(
    currentStep: Int,
    modifier: Modifier = Modifier,
    steps: List<String> = listOf("Role", "Profile", "Address")
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // Stepper Nodes and Responsive Dynamic Connecting Lines
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            steps.forEachIndexed { index, _ ->
                val stepNumber = index + 1
                val isCompleted = stepNumber < currentStep
                val isCurrent = stepNumber == currentStep

                val circleBgColor by animateColorAsState(
                    targetValue = when {
                        isCompleted || isCurrent -> KarmikaColors.Primary
                        else -> KarmikaColors.LightSurfaceVariant
                    },
                    label = "StepperCircleBg_$stepNumber"
                )

                val borderColor by animateColorAsState(
                    targetValue = when {
                        isCurrent -> KarmikaColors.Primary
                        isCompleted -> KarmikaColors.Primary
                        else -> KarmikaColors.Border
                    },
                    label = "StepperBorderColor_$stepNumber"
                )

                // Circle Node
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(circleBgColor)
                        .border(
                            width = if (isCurrent) 2.dp else 1.dp,
                            color = borderColor,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isCompleted) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Step $stepNumber Completed",
                            tint = KarmikaColors.OnPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        Text(
                            text = "$stepNumber",
                            color = if (isCurrent) KarmikaColors.OnPrimary else KarmikaColors.LightTextSecondary,
                            style = KarmikaTypography.Label.copy(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                // Connecting Line between nodes with weight(1f) to flexibly adapt to any screen width
                if (index < steps.size - 1) {
                    val lineColor by animateColorAsState(
                        targetValue = if (stepNumber < currentStep) KarmikaColors.Primary else KarmikaColors.Border.copy(alpha = 0.6f),
                        label = "StepperLineColor_$stepNumber"
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(2.5.dp)
                            .padding(horizontal = 6.dp)
                            .clip(RoundedCornerShape(1.5.dp))
                            .background(lineColor)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Step Labels
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            steps.forEachIndexed { index, label ->
                val stepNumber = index + 1
                val isActive = stepNumber <= currentStep

                Text(
                    text = label,
                    style = KarmikaTypography.Label.copy(
                        fontSize = 12.sp,
                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                    ),
                    color = if (isActive) KarmikaColors.LightTextPrimary else KarmikaColors.LightTextSecondary,
                    textAlign = when (index) {
                        0 -> TextAlign.Start
                        steps.size - 1 -> TextAlign.End
                        else -> TextAlign.Center
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
