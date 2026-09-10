package com.example.karmika.feature.customer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.karmika.app.navigation.KarmikaRoute

private val PrimaryGreen = Color(0xFF00966D)
private val UnselectedGray = Color(0xFF71829D)
private val LightGreenIndicator = Color(0xFFE6F4EA)

@Composable
fun CustomerBottomBar(
    currentRoute: String?,
    onHomeClick: () -> Unit,
    onBookingClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    NavigationBar(
        containerColor = Color.White
    ) {
        val itemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = PrimaryGreen,
            selectedTextColor = PrimaryGreen,
            indicatorColor = LightGreenIndicator,
            unselectedIconColor = UnselectedGray,
            unselectedTextColor = UnselectedGray
        )

        // =================================================
        // HOME
        // =================================================
        NavigationBarItem(
            selected = currentRoute == KarmikaRoute.CustomerHome.route,
            onClick = onHomeClick,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "Home"
                )
            },
            label = {
                Text(text = "Home")
            },
            colors = itemColors
        )

        // =================================================
        // BOOKINGS
        // =================================================
        NavigationBarItem(
            selected = currentRoute == KarmikaRoute.CustomerBooking.route,
            onClick = onBookingClick,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "Bookings"
                )
            },
            label = {
                Text(text = "Bookings")
            },
            colors = itemColors
        )

        // =================================================
        // PROFILE
        // =================================================
        NavigationBarItem(
            selected = currentRoute == KarmikaRoute.CustomerProfile.route,
            onClick = onProfileClick,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Profile"
                )
            },
            label = {
                Text(text = "Profile")
            },
            colors = itemColors
        )
    }
}