package com.example.karmika.feature.customer.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileLogoutButton(
    onLogoutClick: () -> Unit
) {
    // Define custom light red theme colors
    val lightRedBackground = Color(0xFFFFEBEE) // Soft light red
    val redBorder = Color(0xFFFFCDD2)          // Slightly darker light red for border
    val logoutRedColor = Color(0xFFD32F2F)     // Dark red for icon and text contrast

    Button(
        onClick = onLogoutClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, redBorder),
        colors = ButtonDefaults.buttonColors(
            containerColor = lightRedBackground,
            contentColor = logoutRedColor
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Logout,
                contentDescription = "Logout"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Log Out",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}