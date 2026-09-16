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
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
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

// Light Green Theme Palette
private val WhiteBackground = Color(0xFFFFFFFF)
private val LightGreenSurface = Color(0xFFF4FBF7)
private val LightGreenBorder = Color(0xFFE0F2E9)
private val PrimaryGreen = Color(0xFF2E7D32)
private val DarkText = Color(0xFF1B251E)
private val SubtitleText = Color(0xFF6C7A70)
private val DisabledButtonBg = Color(0xFFF1F5F3)
private val DisabledIconColor = Color(0xFFB0BEC5)

@Composable
fun EmployeeCountSection(
    employeeCount: Int,
    onEmployeeCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minimumEmployees: Int = 1,
    maximumEmployees: Int = 10
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, LightGreenBorder, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = WhiteBackground
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Label & Icon Group
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(LightGreenSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Badge,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Number of Workers",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        ),
                        color = DarkText
                    )
                    Text(
                        text = "Select required staff count",
                        style = MaterialTheme.typography.bodySmall,
                        color = SubtitleText
                    )
                }
            }

            // Custom Stepper Controls
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val canDecrease = employeeCount > minimumEmployees
                val canIncrease = employeeCount < maximumEmployees

                val decreaseBtnBg by animateColorAsState(
                    targetValue = if (canDecrease) LightGreenSurface else DisabledButtonBg,
                    label = "DecreaseBgAnim"
                )

                val increaseBtnBg by animateColorAsState(
                    targetValue = if (canIncrease) PrimaryGreen else DisabledButtonBg,
                    label = "IncreaseBgAnim"
                )

                // Decrease Button
                IconButton(
                    onClick = {
                        if (canDecrease) {
                            onEmployeeCountChange(employeeCount - 1)
                        }
                    },
                    enabled = canDecrease,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = decreaseBtnBg,
                        contentColor = PrimaryGreen,
                        disabledContainerColor = DisabledButtonBg,
                        disabledContentColor = DisabledIconColor
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Remove,
                        contentDescription = "Decrease worker count",
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Count Display
                Text(
                    text = employeeCount.toString(),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = DarkText,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                // Increase Button
                IconButton(
                    onClick = {
                        if (canIncrease) {
                            onEmployeeCountChange(employeeCount + 1)
                        }
                    },
                    enabled = canIncrease,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = increaseBtnBg,
                        contentColor = Color.White,
                        disabledContainerColor = DisabledButtonBg,
                        disabledContentColor = DisabledIconColor
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Increase worker count",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}