package com.example.karmika.core.ui.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object KarmikaTypography {

    val Display = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    )

    val Heading1 = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )

    val Heading2 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    val Heading3 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )

    val BodyLarge = TextStyle(
        fontSize = 16.sp
    )

    val BodyMedium = TextStyle(
        fontSize = 14.sp
    )

    val BodySmall = TextStyle(
        fontSize = 12.sp
    )

    val Label = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )

    fun materialTypography(): Typography {
        return Typography(
            displayLarge = Display,
            headlineLarge = Heading1,
            headlineMedium = Heading2,
            headlineSmall = Heading3,
            bodyLarge = BodyLarge,
            bodyMedium = BodyMedium,
            bodySmall = BodySmall,
            labelLarge = Label
        )
    }
}