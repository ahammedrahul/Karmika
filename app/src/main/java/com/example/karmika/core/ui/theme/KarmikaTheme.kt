package com.example.karmika.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.karmika.core.ui.designsystem.KarmikaColors
import com.example.karmika.core.ui.designsystem.KarmikaTypography

private val KarmikaLightColorScheme = lightColorScheme(
    primary = KarmikaColors.Primary,
    onPrimary = KarmikaColors.OnPrimary,

    primaryContainer = KarmikaColors.PrimaryLight,
    onPrimaryContainer = KarmikaColors.PrimaryDark,

    background = KarmikaColors.LightBackground,
    onBackground = KarmikaColors.LightTextPrimary,

    surface = KarmikaColors.LightSurface,
    onSurface = KarmikaColors.LightTextPrimary,

    surfaceVariant = KarmikaColors.LightSurfaceVariant,
    onSurfaceVariant = KarmikaColors.LightTextSecondary,

    error = KarmikaColors.Error
)

private val KarmikaDarkColorScheme = darkColorScheme(
    primary = KarmikaColors.Primary,
    onPrimary = KarmikaColors.OnPrimary,

    primaryContainer = KarmikaColors.PrimaryDark,
    onPrimaryContainer = KarmikaColors.PrimaryLight,

    background = KarmikaColors.DarkBackground,
    onBackground = KarmikaColors.DarkTextPrimary,

    surface = KarmikaColors.DarkSurface,
    onSurface = KarmikaColors.DarkTextPrimary,

    surfaceVariant = KarmikaColors.DarkSurfaceVariant,
    onSurfaceVariant = KarmikaColors.DarkTextSecondary,

    error = KarmikaColors.Error
)
@Composable
fun KarmikaTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (darkTheme) {
            KarmikaDarkColorScheme
        } else {
            KarmikaLightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = KarmikaTypography.materialTypography(),
        content = content
    )
}