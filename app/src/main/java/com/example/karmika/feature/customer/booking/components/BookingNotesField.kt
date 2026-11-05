package com.example.karmika.feature.customer.booking.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val WhiteBackground = Color(0xFFFFFFFF)
private val LightGreenSurface = Color(0xFFF7FCF9)
private val SoftGreen = Color(0xFFE8F6EE)
private val LightGreenBorder = Color(0xFFD7EDE1)
private val PrimaryGreen = Color(0xFF078B5A)
private val DarkText = Color(0xFF10251D)
private val SubtitleText = Color(0xFF718078)
private val ErrorRed = Color(0xFFD32F2F)

const val MAX_NOTES_CHARACTER_LIMIT = 30

@Composable
fun BookingNotesField(
    notes: String,
    onNotesChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxCharLimit: Int = MAX_NOTES_CHARACTER_LIMIT
) {
    val currentLength = notes.length
    val isLimitReached = currentLength >= maxCharLimit

    val counterColor by animateColorAsState(
        targetValue = if (isLimitReached) ErrorRed else SubtitleText,
        label = "CounterColorAnimation"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = LightGreenBorder,
                shape = RoundedCornerShape(18.dp)
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = WhiteBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(SoftGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.EditNote,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Additional Notes",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        ),
                        color = DarkText
                    )

                    Text(
                        text = "Add brief instructions for the professional",
                        style = MaterialTheme.typography.bodySmall,
                        color = SubtitleText
                    )
                }
            }

            // Text Field Section
            OutlinedTextField(
                value = notes,
                onValueChange = { input ->
                    if (input.length <= maxCharLimit) {
                        onNotesChanged(input)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "e.g., Ring the doorbell twice...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SubtitleText.copy(alpha = 0.6f)
                    )
                },
                singleLine = true,
                isError = isLimitReached,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LightGreenSurface,
                    unfocusedContainerColor = LightGreenSurface,
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = LightGreenBorder,
                    focusedTextColor = DarkText,
                    unfocusedTextColor = DarkText,
                    cursorColor = PrimaryGreen,
                    errorBorderColor = ErrorRed
                )
            )

            // Footer with Status and Character Counter
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (isLimitReached) "Limit reached" else "Optional",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isLimitReached) ErrorRed else SubtitleText
                )

                Text(
                    text = "$currentLength / $maxCharLimit",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = if (isLimitReached) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 11.sp
                    ),
                    color = counterColor
                )
            }
        }
    }
}