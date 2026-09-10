package com.example.karmika.feature.customer.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavDestination.Companion.hierarchy

import com.example.karmika.app.navigation.KarmikaRoute

import com.example.karmika.feature.customer.home.CustomerHomeScreen
import com.example.karmika.feature.customer.search.CustomerSearchScreen
import com.example.karmika.feature.customer.booking.CustomerBookingScreen
import com.example.karmika.feature.customer.profile.CustomerProfileScreen
import com.example.karmika.feature.customer.notification.CustomerNotificationScreen


@Composable
fun CustomerNavGraph(
    onLogout: () -> Unit = {}
) {

    // =====================================================
    // NAVIGATION CONTROLLER
    // =====================================================

    val navController = rememberNavController()


    // =====================================================
    // CURRENT DESTINATION
    // =====================================================

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry
            ?.destination
            ?.route


    // =====================================================
    // CUSTOMER SCAFFOLD
    // =====================================================

    Scaffold(

        topBar = {

            CustomerTopBar(

                onNotificationClick = {

                    navController.navigate(
                        KarmikaRoute.CustomerNotifications.route
                    )
                }
            )
        },

        bottomBar = {

            CustomerBottomBar(

                currentRoute = currentRoute,


                // =========================================
                // HOME
                // =========================================

                onHomeClick = {

                    navController.navigate(
                        KarmikaRoute.CustomerHome.route
                    ) {

                        popUpTo(
                            KarmikaRoute.CustomerHome.route
                        ) {

                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },


                // =========================================
                // BOOKINGS
                // =========================================

                onBookingClick = {

                    navController.navigate(
                        KarmikaRoute.CustomerBooking.route
                    ) {

                        popUpTo(
                            KarmikaRoute.CustomerHome.route
                        ) {

                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },


                // =========================================
                // PROFILE
                // =========================================

                onProfileClick = {

                    navController.navigate(
                        KarmikaRoute.CustomerProfile.route
                    ) {

                        popUpTo(
                            KarmikaRoute.CustomerHome.route
                        ) {

                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->


        // =================================================
        // CUSTOMER NAV HOST
        // =================================================

        NavHost(

            navController = navController,

            startDestination =
                KarmikaRoute.CustomerHome.route,

            modifier = Modifier.padding(
                innerPadding
            )
        ) {


            // =============================================
            // HOME
            // =============================================

            composable(
                route = KarmikaRoute.CustomerHome.route
            ) {

                CustomerHomeScreen(

                    onSearchClick = {

                        navController.navigate(
                            KarmikaRoute.CustomerSearch.route
                        )
                    }
                )
            }


            // =============================================
            // SEARCH
            // =============================================

            composable(
                route = KarmikaRoute.CustomerSearch.route
            ) {

                CustomerSearchScreen(

                    onBack = {

                        navController.popBackStack()
                    }
                )
            }


            // =============================================
            // BOOKINGS
            // =============================================

            composable(
                route = KarmikaRoute.CustomerBooking.route
            ) {

                CustomerBookingScreen()
            }


            // =============================================
            // PROFILE
            // =============================================

            composable(
                route = KarmikaRoute.CustomerProfile.route
            ) {

                CustomerProfileScreen(

                    onLogout = {

                        onLogout()
                    }
                )
            }


            // =============================================
            // NOTIFICATIONS
            // =============================================

            composable(
                route =
                    KarmikaRoute.CustomerNotifications.route
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