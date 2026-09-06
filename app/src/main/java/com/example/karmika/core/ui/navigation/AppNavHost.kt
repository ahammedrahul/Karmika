package com.example.karmika.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.karmika.core.ui.navigation.KarmikaRoute
import com.example.karmika.feature.auth.login.LoginScreen
import com.example.karmika.feature.auth.register.RegisterScreen
import com.example.karmika.feature.auth.register.RegisterViewModel

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = KarmikaRoute.Register.route
    ) {

        // -----------------------------------------
        // REGISTER
        // -----------------------------------------

        composable(
            route = KarmikaRoute.Register.route
        ) {

            RegisterScreen(
                viewModel = viewModel(),

                onLoginClick = {
                    navController.navigate(
                        KarmikaRoute.Login.route
                    )
                },

                onRegistrationSuccess = {
                    navController.navigate(
                        KarmikaRoute.Otp.route
                    )
                }
            )
        }


        // -----------------------------------------
        // LOGIN
        // -----------------------------------------

        composable(
            route = KarmikaRoute.Login.route
        ) {

            LoginScreen(
                viewModel = androidx.lifecycle.viewmodel.compose.viewModel(),

                onRegisterClick = {
                    navController.navigate(
                        KarmikaRoute.Register.route
                    )
                },

                onForgotPasswordClick = {
                    // We will implement this later
                },

                onLoginSuccess = {
                    // We will navigate to the appropriate
                    // Customer / Worker / Company screen later
                }
            )
        }


        // -----------------------------------------
        // OTP
        // -----------------------------------------

        composable(
            route = KarmikaRoute.Otp.route
        ) {

            Text(
                text = "OTP Screen"
            )
        }


        // -----------------------------------------
        // ONBOARDING
        // -----------------------------------------

        composable(
            route = KarmikaRoute.Onboarding.route
        ) {

            Text(
                text = "Onboarding Screen"
            )
        }


        // -----------------------------------------
        // CUSTOMER
        // -----------------------------------------

        composable(
            route = KarmikaRoute.CustomerHome.route
        ) {

            Text(
                text = "Customer Home"
            )
        }


        // -----------------------------------------
        // WORKER
        // -----------------------------------------

        composable(
            route = KarmikaRoute.WorkerHome.route
        ) {

            Text(
                text = "Worker Home"
            )
        }


        // -----------------------------------------
        // COMPANY
        // -----------------------------------------

        composable(
            route = KarmikaRoute.CompanyDashboard.route
        ) {

            Text(
                text = "Company Dashboard"
            )
        }


        // -----------------------------------------
        // ADMIN - FUTURE
        // -----------------------------------------

        composable(
            route = KarmikaRoute.AdminDashboard.route
        ) {

            Text(
                text = "Admin Dashboard"
            )
        }


        // -----------------------------------------
        // SUPER ADMIN - FUTURE
        // -----------------------------------------

        composable(
            route = KarmikaRoute.SuperAdminDashboard.route
        ) {

            Text(
                text = "Super Admin Dashboard"
            )
        }
    }
}