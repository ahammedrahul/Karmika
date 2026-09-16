package com.example.karmika.feature.customer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.karmika.app.navigation.KarmikaRoute
import com.example.karmika.feature.customer.booking.BookingFormScreen
import com.example.karmika.feature.customer.booking.CustomerBookingScreen
import com.example.karmika.feature.customer.booking_confirmation.BookingConfirmationScreen
import com.example.karmika.feature.customer.home.CustomerHomeScreen
import com.example.karmika.feature.customer.notification.CustomerNotificationScreen
import com.example.karmika.feature.customer.profile.CustomerProfileScreen
import com.example.karmika.feature.customer.search.CustomerSearchScreen
import com.example.karmika.feature.customer.service_details.ServiceDetailsScreen
import com.example.karmika.feature.customer.services.ServicesScreen

@Composable
fun CustomerNavGraph(
    onLogout: () -> Unit = {}
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isFullScreenRoute = currentRoute in setOf(
        KarmikaRoute.CustomerSearch.route,
        KarmikaRoute.CustomerNotifications.route,
        KarmikaRoute.CustomerServiceDetails.route,
        KarmikaRoute.CustomerBookingForm.route,
        KarmikaRoute.CustomerBookingConfirmation.route
    )

    Scaffold(
        topBar = {
            if (!isFullScreenRoute) {
                CustomerTopBar(
                    onNotificationClick = {
                        navController.navigate(
                            KarmikaRoute.CustomerNotifications.route
                        )
                    }
                )
            }
        },

        bottomBar = {
            if (!isFullScreenRoute) {
                CustomerBottomBar(
                    currentRoute = currentRoute,

                    onHomeClick = {
                        navController.popBackStack(
                            KarmikaRoute.CustomerHome.route,
                            inclusive = false
                        )
                    },

                    onBookingClick = {
                        navigateToMainTab(
                            navController = navController,
                            route = KarmikaRoute.CustomerBooking.route
                        )
                    },

                    onProfileClick = {
                        navigateToMainTab(
                            navController = navController,
                            route = KarmikaRoute.CustomerProfile.route
                        )
                    }
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = KarmikaRoute.CustomerHome.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            // ==================================================
            // HOME
            // ==================================================

            composable(
                route = KarmikaRoute.CustomerHome.route
            ) {
                CustomerHomeScreen(

                    onSearchClick = {
                        navController.navigate(
                            KarmikaRoute.CustomerSearch.route
                        )
                    },

                    // Category → Services List
                    onServiceClick = { categoryId ->
                        navController.navigate(
                            KarmikaRoute.CustomerServices.createRoute(
                                categoryId
                            )
                        )
                    },

                    // Popular Service → Service Details
                    onPopularServiceClick = { serviceId ->
                        navController.navigate(
                            KarmikaRoute.CustomerServiceDetails.createRoute(
                                serviceId
                            )
                        )
                    }
                )
            }

            // ==================================================
            // SEARCH
            // ==================================================

            composable(
                route = KarmikaRoute.CustomerSearch.route
            ) {
                CustomerSearchScreen(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            // ==================================================
            // SERVICES LIST
            // ==================================================

            composable(
                route = KarmikaRoute.CustomerServices.route,
                arguments = listOf(
                    navArgument("categoryId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val categoryId =
                    backStackEntry.arguments
                        ?.getString("categoryId")
                        ?: "all"

                ServicesScreen(
                    categoryId = categoryId,

                    onServiceClick = { service ->
                        navController.navigate(
                            KarmikaRoute.CustomerServiceDetails.createRoute(
                                service.id
                            )
                        )
                    },

                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            // ==================================================
            // SERVICE DETAILS
            // ==================================================

            composable(
                route = KarmikaRoute.CustomerServiceDetails.route,
                arguments = listOf(
                    navArgument("serviceId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val serviceId =
                    backStackEntry.arguments
                        ?.getString("serviceId")
                        ?: return@composable

                ServiceDetailsScreen(
                    serviceId = serviceId,

                    onBackClick = {
                        navController.popBackStack()
                    },

                    onCloseClick = {
                        navController.popBackStack(
                            KarmikaRoute.CustomerHome.route,
                            inclusive = false
                        )
                    },

                    onBookNowClick = {
                        navController.navigate(
                            KarmikaRoute.CustomerBookingForm.createRoute(
                                serviceId
                            )
                        )
                    }
                )
            }

            // ==================================================
            // BOOKING FORM
            // ==================================================

            composable(
                route = KarmikaRoute.CustomerBookingForm.route,
                arguments = listOf(
                    navArgument("serviceId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val serviceId =
                    backStackEntry.arguments
                        ?.getString("serviceId")
                        ?: return@composable

                BookingFormScreen(
                    serviceId = serviceId,

                    onBackClick = {
                        navController.popBackStack()
                    },

                    onBookingConfirmed = {
                        navController.navigate(
                            KarmikaRoute.CustomerBookingConfirmation.route
                        ) {
                            popUpTo(
                                KarmikaRoute.CustomerBookingForm.route
                            ) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            // ==================================================
            // BOOKING CONFIRMATION
            // ==================================================

            composable(
                route = KarmikaRoute.CustomerBookingConfirmation.route
            ) {
                BookingConfirmationScreen(
                    bookingId = "REQ-1001",
                    serviceName = "Home Cleaning",
                    companyName = "Karmika Home Experts",
                    bookingDate = "20 September 2026",
                    bookingTime = "10:00 AM",
                    address = "123 Main Street, Bengaluru",
                    employeeCount = 2,
                    totalAmount = 1798,
                    status = "PENDING",

                    onViewBookingsClick = {
                        navigateToMainTab(
                            navController = navController,
                            route = KarmikaRoute.CustomerBooking.route
                        )
                    },

                    onBackToHomeClick = {
                        navController.popBackStack(
                            KarmikaRoute.CustomerHome.route,
                            inclusive = false
                        )
                    }
                )
            }

            // ==================================================
            // BOOKINGS
            // ==================================================

            composable(
                route = KarmikaRoute.CustomerBooking.route
            ) {
                CustomerBookingScreen()
            }

            // ==================================================
            // PROFILE
            // ==================================================

            composable(
                route = KarmikaRoute.CustomerProfile.route
            ) {
                CustomerProfileScreen(
                    onLogout = onLogout
                )
            }

            // ==================================================
            // NOTIFICATIONS
            // ==================================================

            composable(
                route = KarmikaRoute.CustomerNotifications.route
            ) {
                CustomerNotificationScreen(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

// ==========================================================
// MAIN BOTTOM NAVIGATION
// ==========================================================

private fun navigateToMainTab(
    navController: NavHostController,
    route: String
) {
    navController.navigate(route) {

        popUpTo(
            KarmikaRoute.CustomerHome.route
        ) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }
}