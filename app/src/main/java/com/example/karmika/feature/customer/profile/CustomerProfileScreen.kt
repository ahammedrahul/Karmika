package com.example.karmika.feature.customer.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.core.ui.designsystem.KarmikaColors

@Composable
fun CustomerProfileScreen(
    onLogout: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = KarmikaColors.LightBackground
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Title
            item {
                Text(
                    text = "Profile",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = KarmikaColors.LightTextPrimary
                )
            }

            // Premium Profile Header Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = KarmikaColors.PrimaryLight),
                    border = BorderStroke(1.dp, KarmikaColors.Primary.copy(alpha = 0.2f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Circular Avatar with Overlay Edit Icon
                        Box(
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(68.dp)
                                    .clip(CircleShape)
                                    .background(KarmikaColors.PrimaryLight)
                                    .border(2.dp, KarmikaColors.Primary, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = "Profile Picture",
                                    tint = KarmikaColors.Primary,
                                    modifier = Modifier.size(38.dp)
                                )
                            }

                            // Small Edit Floating Badge on Image
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(KarmikaColors.Primary)
                                    .border(2.dp, KarmikaColors.OnPrimary, CircleShape)
                                    .clickable { },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "Edit Profile Picture",
                                    tint = KarmikaColors.OnPrimary,
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // User Info
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Rahul Sharma",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = KarmikaColors.LightTextPrimary
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "rahul.sharma@email.com",
                                fontSize = 13.sp,
                                color = KarmikaColors.LightTextSecondary
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "+91 98765 43210",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = KarmikaColors.Primary
                            )
                        }
                    }
                }
            }

            // Account Section
            item {
                Column {
                    Text(
                        text = "Account",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = KarmikaColors.LightTextPrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = KarmikaColors.LightSurface),
                        border = BorderStroke(1.dp, KarmikaColors.Border)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 14.dp)
                        ) {
                            ProfileMenuItem(
                                icon = Icons.Outlined.Person,
                                title = "Personal Information",
                                subtitle = "Name, email and phone number",
                                showDivider = true
                            )

                            ProfileMenuItem(
                                icon = Icons.Outlined.LocationOn,
                                title = "My Addresses",
                                subtitle = "Manage saved home & work locations",
                                showDivider = true
                            )

                            ProfileMenuItem(
                                icon = Icons.Outlined.Settings,
                                title = "App Settings",
                                subtitle = "Notifications and display preferences",
                                showDivider = false
                            )
                        }
                    }
                }
            }

            // Support & Security Section
            item {
                Column {
                    Text(
                        text = "Preferences & Support",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = KarmikaColors.LightTextPrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = KarmikaColors.LightSurface),
                        border = BorderStroke(1.dp, KarmikaColors.Border)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 14.dp)
                        ) {
                            ProfileMenuItem(
                                icon = Icons.Outlined.Shield,
                                title = "Privacy & Security",
                                subtitle = "Password and account security",
                                showDivider = true
                            )

                            ProfileMenuItem(
                                icon = Icons.Outlined.HelpOutline,
                                title = "Help & Support",
                                subtitle = "FAQs and customer support",
                                showDivider = false
                            )
                        }
                    }
                }
            }

            // Logout Option
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(KarmikaColors.ErrorLight)
                        .clickable { onLogout() }
                        .padding(horizontal = 18.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Logout,
                        contentDescription = "Logout",
                        tint = KarmikaColors.Error,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Log Out",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = KarmikaColors.Error
                    )
                }
            }
        }
    }
}

// Reusable Clean Menu Item Component
@Composable
private fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    showDivider: Boolean = true
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(vertical = 14.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(KarmikaColors.PrimaryLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = KarmikaColors.Primary,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = KarmikaColors.LightTextPrimary
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = KarmikaColors.LightTextSecondary
                )
            }

            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(KarmikaColors.LightSurfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open",
                    tint = KarmikaColors.LightTextSecondary,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        if (showDivider) {
            HorizontalDivider(
                color = KarmikaColors.Border.copy(alpha = 0.5f),
                thickness = 1.dp
            )
        }
    }
}