package com.example.karmika.feature.customer.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apartment
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.HeadsetMic
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.feature.customer.profile.components.ProfileHeader
import com.example.karmika.feature.customer.profile.components.ProfileLogoutButton
import com.example.karmika.feature.customer.profile.components.ProfileMenuItemData
import com.example.karmika.feature.customer.profile.components.ProfileMenuSection

@Composable
fun CustomerProfileScreen(
    modifier: Modifier = Modifier,
    onEditProfileClick: () -> Unit = {},
    onSavedAddressesClick: () -> Unit = {},
    onFavoriteServicesClick: () -> Unit = {},
    onFavoriteCompaniesClick: () -> Unit = {},
    onPaymentClick: () -> Unit = {},
    onSecurityClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onHelpSupportClick: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val screenBg = Color(0xFFF7FBF9)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(screenBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // ---------------------------------------------------------
        // SECTION TITLE & SUBTITLE
        // ---------------------------------------------------------
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "My Profile",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                color = Color(0xFF0F172A)
            )

            Text(
                text = "Manage your account and preferences",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                color = Color(0xFF64748B)
            )
        }

        // ---------------------------------------------------------
        // PROFILE HEADER CARD
        // ---------------------------------------------------------
        ProfileHeader(
            fullName = "Rahul Ahammed",
            email = "rahul@example.com",
            phone = "+91 98765 43210",
            location = "Bengaluru, Karnataka",
            onEditProfileClick = onEditProfileClick
        )

        // ---------------------------------------------------------
        // ADDRESS
        // ---------------------------------------------------------
        ProfileMenuSection(
            sectionTitle = "Address",
            onManageClick = onSavedAddressesClick,
            items = listOf(
                ProfileMenuItemData(
                    icon = Icons.Rounded.Home,
                    title = "Saved Addresses",
                    subtitle = "Manage your home, work and other addresses",
                    iconTint = Color(0xFF0D6338),
                    iconBgColor = Color(0xFFEAF5EF),
                    onClick = onSavedAddressesClick
                )
            )
        )

        // ---------------------------------------------------------
        // SAVED & FAVORITES
        // ---------------------------------------------------------
        ProfileMenuSection(
            sectionTitle = "Saved & Favorites",
            isGrid = true,
            items = listOf(
                ProfileMenuItemData(
                    icon = Icons.Rounded.Favorite,
                    title = "Favorite Services",
                    subtitle = "View and manage your saved services",
                    iconTint = Color(0xFFEF4444),
                    iconBgColor = Color(0xFFFEE2E2),
                    onClick = onFavoriteServicesClick
                ),
                ProfileMenuItemData(
                    icon = Icons.Rounded.Apartment,
                    title = "Favorite Companies",
                    subtitle = "View and manage your favorite companies",
                    iconTint = Color(0xFF8B5CF6),
                    iconBgColor = Color(0xFFF3E8FF),
                    onClick = onFavoriteCompaniesClick
                )
            )
        )

        // ---------------------------------------------------------
        // PAYMENT & BILLING
        // ---------------------------------------------------------
        ProfileMenuSection(
            sectionTitle = "Payment & Billing",
            onManageClick = onPaymentClick,
            items = listOf(
                ProfileMenuItemData(
                    icon = Icons.Rounded.CreditCard,
                    title = "Payment Methods",
                    subtitle = "Manage your payment methods, view history and invoices",
                    iconTint = Color(0xFF10B981),
                    iconBgColor = Color(0xFFD1FAE5),
                    onClick = onPaymentClick
                )
            )
        )

        // ---------------------------------------------------------
        // SECURITY & ACCOUNT
        // ---------------------------------------------------------
        ProfileMenuSection(
            sectionTitle = "Security & Account",
            items = listOf(
                ProfileMenuItemData(
                    icon = Icons.Rounded.Lock,
                    title = "Account Security",
                    subtitle = "Change password, update email/phone, manage account settings",
                    iconTint = Color(0xFFF59E0B),
                    iconBgColor = Color(0xFFFEF3C7),
                    onClick = onSecurityClick
                )
            )
        )

        // ---------------------------------------------------------
        // APP SETTINGS
        // ---------------------------------------------------------
        ProfileMenuSection(
            sectionTitle = "App Settings",
            items = listOf(
                ProfileMenuItemData(
                    icon = Icons.Rounded.Settings,
                    title = "App Preferences",
                    subtitle = "Language, theme, location and privacy settings",
                    iconTint = Color(0xFF3B82F6),
                    iconBgColor = Color(0xFFDBEAFE),
                    onClick = onSettingsClick
                )
            )
        )

        // ---------------------------------------------------------
        // HELP & SUPPORT
        // ---------------------------------------------------------
        ProfileMenuSection(
            sectionTitle = "Help & Support",
            items = listOf(
                ProfileMenuItemData(
                    icon = Icons.Rounded.HeadsetMic,
                    title = "Help & Support",
                    subtitle = "FAQs, contact support, terms & policies",
                    iconTint = Color(0xFF0D6338),
                    iconBgColor = Color(0xFFEAF5EF),
                    onClick = onHelpSupportClick
                )
            )
        )

        // ---------------------------------------------------------
        // LOGOUT BUTTON
        // ---------------------------------------------------------
        Spacer(modifier = Modifier.height(4.dp))

        ProfileLogoutButton(
            onLogoutClick = onLogout
        )
    }
}