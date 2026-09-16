package com.example.karmika.feature.customer.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileHeader(
    fullName: String,
    email: String,
    phone: String,
    location: String,
    onEditProfileClick: () -> Unit,
    onChangePhotoClick: () -> Unit = {}
) {
    // Custom Color Palette based on design
    val primaryGreen = Color(0xFF0D6338)
    val buttonBgGreen = Color(0xFFEAF5EF)
    val textMuted = Color(0xFF5A6B82)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 0.5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // ---------------------------------------------------------
            // PROFILE IMAGE CONTAINER
            // ---------------------------------------------------------
            Box(
                modifier = Modifier.size(80.dp)
            ) {
                // Avatar placeholder (Replace with AsyncImage/Coil for network image)
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFB2C8C6)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "Profile Photo",
                        modifier = Modifier.size(48.dp),
                        tint = Color.White
                    )
                }

                // Camera Action Button Badge
                Surface(
                    modifier = Modifier
                        .size(26.dp)
                        .align(Alignment.BottomEnd),
                    shape = CircleShape,
                    color = primaryGreen,
                    border = BorderStroke(2.dp, Color.White)
                ) {
                    IconButton(
                        onClick = onChangePhotoClick,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.CameraAlt,
                            contentDescription = "Change Profile Photo",
                            modifier = Modifier.size(13.dp),
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            // ---------------------------------------------------------
            // PROFILE DETAILS
            // ---------------------------------------------------------
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = fullName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    color = Color(0xFF0F172A),
                    maxLines = 1
                )

                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                    color = textMuted,
                    maxLines = 1
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Phone,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = textMuted
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = phone,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
                        color = textMuted,
                        maxLines = 1
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = textMuted
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = location,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
                        color = textMuted,
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // ---------------------------------------------------------
            // EDIT PROFILE BUTTON
            // ---------------------------------------------------------
            FilledTonalButton(
                onClick = onEditProfileClick,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = buttonBgGreen,
                    contentColor = primaryGreen
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Edit Profile",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
        }
    }
}