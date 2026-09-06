package com.example.karmika.core.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.core.ui.designsystem.KarmikaColors

@Composable
fun KarmikaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        enabled = enabled && !isLoading,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = KarmikaColors.Primary
        ),
        contentPadding = ButtonDefaults.ContentPadding
    ) {

        if (isLoading) {

            CircularProgressIndicator(
                modifier = Modifier.height(20.dp),
                strokeWidth = 2.dp
            )

        } else {

            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}