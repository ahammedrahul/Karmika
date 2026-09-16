package com.example.karmika.feature.customer.booking_confirmation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Light Green Theme Tokens
private val LightGreenGlow = Color(0xFFE8F5E9)
private val LightGreenRing = Color(0xFFC8E6C9)
private val PrimaryGreen = Color(0xFF2E7D32)
private val WhiteBackground = Color(0xFFFFFFFF)

@Composable
fun BookingSuccessIcon(
    modifier: Modifier = Modifier
) {
    var isAnimated by remember { mutableStateOf(false) }

    // Subtle scale spring animation on render
    val scaleState by animateFloatAsState(
        targetValue = if (isAnimated) 1f else 0.4f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "SuccessIconScale"
    )

    LaunchedEffect(Unit) {
        isAnimated = true
    }

    Box(
        modifier = modifier
            .scale(scaleState),
        contentAlignment = Alignment.Center
    ) {
        // Outer soft glow aura
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(LightGreenGlow)
        )

        // Middle accent ring
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(LightGreenRing)
        )

        // Primary solid checkmark badge
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(PrimaryGreen),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = "Request submitted successfully",
                tint = WhiteBackground,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}