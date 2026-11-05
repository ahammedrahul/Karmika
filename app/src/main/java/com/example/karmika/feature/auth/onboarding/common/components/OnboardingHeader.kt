package com.example.karmika.feature.auth.onboarding.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.karmika.R
import com.example.karmika.core.ui.designsystem.KarmikaColors
import com.example.karmika.core.ui.designsystem.KarmikaDimensions
import com.example.karmika.core.ui.designsystem.KarmikaTypography

@Composable
fun OnboardingHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
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
            style = KarmikaTypography.Heading2,
            color = KarmikaColors.LightTextPrimary
        )
    }
}
