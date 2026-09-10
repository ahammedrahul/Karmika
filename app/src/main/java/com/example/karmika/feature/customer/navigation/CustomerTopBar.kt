package com.example.karmika.feature.customer.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomerTopBar(
    onNotificationClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // =================================================
        // KARMika BRAND
        // =================================================

        Text(
            text = "Karmika",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )


        // =================================================
        // NOTIFICATION
        // =================================================

        IconButton(
            onClick = onNotificationClick
        ) {

            Icon(
                imageVector = Icons.Outlined.NotificationsNone,
                contentDescription = "Notifications"
            )
        }
    }
}