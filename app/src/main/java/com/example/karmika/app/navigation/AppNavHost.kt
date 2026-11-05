package com.example.karmika.app.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.karmika.core.common.model.UserRole
import com.example.karmika.feature.splash.SplashScreen
import com.example.karmika.feature.auth.login.LoginScreen
import com.example.karmika.feature.auth.login.LoginViewModel
import com.example.karmika.feature.auth.register.RegisterScreen
import com.example.karmika.feature.auth.register.RegisterViewModel
import com.example.karmika.feature.auth.onboarding.common.OnboardingRoleScreen
import com.example.karmika.feature.auth.onboarding.common.profile.ProfileStepScreen
import com.example.karmika.feature.auth.onboarding.common.profile.ProfileStepViewModel
import com.example.karmika.feature.auth.onboarding.customer.CustomerOnboardingScreen
import com.example.karmika.feature.auth.onboarding.customer.CustomerOnboardingViewModel
import com.example.karmika.feature.customer.navigation.CustomerNavGraph

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = KarmikaRoute.Splash.route
    ) {

        // Splash
        composable(KarmikaRoute.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigateToLoginFromSplash()
                }
            )
        }

        // Login
        composable(KarmikaRoute.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()

            LoginScreen(
                viewModel = viewModel,
                onRegisterClick = {
                    navController.navigate(KarmikaRoute.Register.route) {
                        launchSingleTop = true
                    }
                },
                onForgotPasswordClick = {
                    // Forgot password will be implemented later.
                },
                onLoginSuccess = {
                    navController.navigateToOnboardingFromLogin()
                }
            )
        }

        // Register
        composable(KarmikaRoute.Register.route) {
            val viewModel: RegisterViewModel = hiltViewModel()

            RegisterScreen(
                viewModel = viewModel,
                onLoginClick = {
                    navController.navigateToLoginFromRegister()
                },
                onRegistrationSuccess = {
                    navController.navigateToOnboardingFromRegister()
                }
            )
        }

        // Role selection
        composable(KarmikaRoute.Onboarding.route) {
            OnboardingRoleScreen(
                onContinue = { selectedRole ->
                    when (selectedRole) {
                        UserRole.HOUSEHOLD,
                        UserRole.WORKER,
                        UserRole.COMPANY,
                        UserRole.ORGANIZATION -> {
                            navController.navigate(
                                KarmikaRoute.ProfileStep.route
                            )
                        }
                    }
                }
            )
        }

        // Common profile step
        composable(KarmikaRoute.ProfileStep.route) {
            val viewModel: ProfileStepViewModel = hiltViewModel()

            ProfileStepScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                },
                onContinue = {
                    navController.navigate(
                        KarmikaRoute.CustomerOnboarding.route
                    )
                }
            )
        }

        // Customer onboarding
        composable(KarmikaRoute.CustomerOnboarding.route) {
            val viewModel: CustomerOnboardingViewModel = viewModel()

            CustomerOnboardingScreen(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                },
                onContinue = {
                    navController.navigate(
                        KarmikaRoute.CustomerApp.route
                    ) {
                        popUpTo(
                            KarmikaRoute.CustomerOnboarding.route
                        ) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Customer application
        composable(KarmikaRoute.CustomerApp.route) {
            CustomerNavGraph(
                onLogout = {
                    navController.navigateToLoginFromCustomer()
                }
            )
        }

        // Worker routes
        composable(KarmikaRoute.WorkerOnboarding.route) {
            Text(text = "Worker Onboarding")
        }

        composable(KarmikaRoute.WorkerHome.route) {
            Text(text = "Worker Home")
        }

        composable(KarmikaRoute.WorkerJobs.route) {
            Text(text = "Worker Jobs")
        }

        composable(KarmikaRoute.WorkerEarnings.route) {
            Text(text = "Worker Earnings")
        }

        composable(KarmikaRoute.WorkerProfile.route) {
            Text(text = "Worker Profile")
        }

        // Company routes
        composable(KarmikaRoute.CompanyOnboarding.route) {
            Text(text = "Company Onboarding")
        }

        composable(KarmikaRoute.CompanyDashboard.route) {
            Text(text = "Company Dashboard")
        }

        composable(KarmikaRoute.CompanyWorkers.route) {
            Text(text = "Company Workers")
        }

        composable(KarmikaRoute.CompanyJobs.route) {
            Text(text = "Company Jobs")
        }

        composable(KarmikaRoute.CompanyProfile.route) {
            Text(text = "Company Profile")
        }

        // Other routes
        composable(KarmikaRoute.Otp.route) {
            Text(text = "OTP Screen")
        }

        composable(KarmikaRoute.AdminDashboard.route) {
            Text(text = "Admin Dashboard")
        }

        composable(KarmikaRoute.SuperAdminDashboard.route) {
            Text(text = "Super Admin Dashboard")
        }
    }
}

private fun NavHostController.navigateToLoginFromSplash() {
    navigate(KarmikaRoute.Login.route) {
        popUpTo(KarmikaRoute.Splash.route) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

private fun NavHostController.navigateToLoginFromRegister() {
    navigate(KarmikaRoute.Login.route) {
        popUpTo(KarmikaRoute.Register.route) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

private fun NavHostController.navigateToOnboardingFromRegister() {
    navigate(KarmikaRoute.Onboarding.route) {
        popUpTo(KarmikaRoute.Register.route) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

private fun NavHostController.navigateToOnboardingFromLogin() {
    navigate(KarmikaRoute.Onboarding.route) {
        popUpTo(KarmikaRoute.Login.route) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

private fun NavHostController.navigateToLoginFromCustomer() {
    navigate(KarmikaRoute.Login.route) {
        popUpTo(KarmikaRoute.CustomerApp.route) {
            inclusive = true
        }
        launchSingleTop = true
    }
}