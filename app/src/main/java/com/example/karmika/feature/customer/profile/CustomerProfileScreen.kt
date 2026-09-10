package com.example.karmika.feature.customer.profile

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
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color Palette
private val PrimaryGreen = Color(0xFF00966D)
private val LightGreenCard = Color(0xFFE6F4EA)
private val LightGreenTint = Color(0xFFC8E6C9)
private val DarkText = Color(0xFF182236)
private val SecondaryText = Color(0xFF64748B)
private val CardBorderColor = Color(0xFFD3ECE1)
private val BackgroundColor = Color(0xFFF8FAFC)
private val LogoutRed = Color(0xFFE53935)
private val LogoutRedBg = Color(0xFFFDE8E8)

@Composable
fun CustomerProfileScreen(
    onLogout: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
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
                    color = DarkText
                )
            }

            // Premium Profile Header Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = LightGreenCard),
                    border = androidx.compose.foundation.BorderStroke(1.dp, CardBorderColor)
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
                                    .background(LightGreenTint)
                                    .border(2.dp, PrimaryGreen, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Person,
                                    contentDescription = "Profile Picture",
                                    tint = PrimaryGreen,
                                    modifier = Modifier.size(38.dp)
                                )
                            }

                            // Small Edit Floating Badge on Image
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryGreen)
                                    .border(2.dp, Color.White, CircleShape)
                                    .clickable { },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "Edit Profile Picture",
                                    tint = Color.White,
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
                                color = DarkText
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "rahul.sharma@email.com",
                                fontSize = 13.sp,
                                color = SecondaryText
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "+91 98765 43210",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = PrimaryGreen
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
                        color = DarkText
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
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
                        color = DarkText
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
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
                        .background(LogoutRedBg)
                        .clickable { onLogout() }
                        .padding(horizontal = 18.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Logout,
                        contentDescription = "Logout",
                        tint = LogoutRed,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Log Out",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = LogoutRed
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
                    .background(LightGreenCard),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = PrimaryGreen,
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
                    color = DarkText
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = SecondaryText
                )
            }

            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(BackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Open",
                    tint = SecondaryText,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        if (showDivider) {
            androidx.compose.material3.HorizontalDivider(
                color = Color(0xFFF1F5F9),
                thickness = 1.dp
            )
        }
    }
}