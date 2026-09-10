package com.example.karmika.app.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.karmika.core.common.model.UserRole
import com.example.karmika.data.local.LocalUserStorage

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

    // =====================================================
    // NAVIGATION CONTROLLER
    // =====================================================

    val navController = rememberNavController()


    // =====================================================
    // LOCAL USER STORAGE
    // =====================================================

    val context = LocalContext.current

    val localUserStorage = LocalUserStorage(context)


    // =====================================================
    // APP NAVIGATION HOST
    // =====================================================

    NavHost(

        navController = navController,

        startDestination = KarmikaRoute.Splash.route
    ) {


        // =================================================
        // SPLASH
        // =================================================

        composable(
            route = KarmikaRoute.Splash.route
        ) {

            SplashScreen(

                onSplashFinished = {

                    navController.navigate(
                        KarmikaRoute.Login.route
                    ) {

                        popUpTo(
                            KarmikaRoute.Splash.route
                        ) {

                            inclusive = true
                        }
                    }
                }
            )
        }


        // =================================================
        // LOGIN
        // =================================================

        composable(
            route = KarmikaRoute.Login.route
        ) {

            val loginViewModel: LoginViewModel =
                viewModel(
                    factory = LoginViewModel.Factory(
                        localUserStorage
                    )
                )


            LoginScreen(

                viewModel = loginViewModel,


                // -----------------------------------------
                // REGISTER
                // -----------------------------------------

                onRegisterClick = {

                    navController.navigate(
                        KarmikaRoute.Register.route
                    )
                },


                // -----------------------------------------
                // FORGOT PASSWORD
                // -----------------------------------------

                onForgotPasswordClick = {

                    // TODO:
                    // Forgot password will be implemented later.
                },


                // -----------------------------------------
                // LOGIN SUCCESS
                // -----------------------------------------

                onLoginSuccess = {

                    navController.navigate(
                        KarmikaRoute.Onboarding.route
                    ) {

                        popUpTo(
                            KarmikaRoute.Login.route
                        ) {

                            inclusive = true
                        }
                    }
                }
            )
        }


        // =================================================
        // REGISTER
        // =================================================

        composable(
            route = KarmikaRoute.Register.route
        ) {

            val registerViewModel: RegisterViewModel =
                viewModel(
                    factory = RegisterViewModel.Factory(
                        localUserStorage
                    )
                )


            RegisterScreen(

                viewModel = registerViewModel,


                // -----------------------------------------
                // GO TO LOGIN
                // -----------------------------------------

                onLoginClick = {

                    navController.navigate(
                        KarmikaRoute.Login.route
                    )
                },


                // -----------------------------------------
                // REGISTRATION SUCCESS
                // -----------------------------------------

                onRegistrationSuccess = {

                    navController.navigate(
                        KarmikaRoute.Onboarding.route
                    ) {

                        popUpTo(
                            KarmikaRoute.Register.route
                        ) {

                            inclusive = true
                        }
                    }
                }
            )
        }


        // =================================================
        // ROLE SELECTION
        // =================================================

        composable(
            route = KarmikaRoute.Onboarding.route
        ) {

            OnboardingRoleScreen(

                onContinue = { selectedRole ->

                    when (selectedRole) {


                        // =================================
                        // CUSTOMER
                        // =================================

                        UserRole.HOUSEHOLD -> {

                            navController.navigate(
                                KarmikaRoute.ProfileStep.route
                            )
                        }


                        // =================================
                        // WORKER
                        // =================================

                        UserRole.WORKER -> {

                            navController.navigate(
                                KarmikaRoute.WorkerOnboarding.route
                            )
                        }


                        // =================================
                        // COMPANY
                        // =================================

                        UserRole.COMPANY -> {

                            navController.navigate(
                                KarmikaRoute.CompanyOnboarding.route
                            )
                        }


                        // =================================
                        // ORGANIZATION
                        // =================================

                        UserRole.ORGANIZATION -> {

                            navController.navigate(
                                KarmikaRoute.CompanyOnboarding.route
                            )
                        }
                    }
                }
            )
        }


        // =================================================
        // COMMON PROFILE STEP
        // =================================================

        composable(
            route = KarmikaRoute.ProfileStep.route
        ) {

            val profileViewModel: ProfileStepViewModel =
                viewModel()


            ProfileStepScreen(

                viewModel = profileViewModel,


                // -----------------------------------------
                // BACK
                // -----------------------------------------

                onBack = {

                    navController.popBackStack()
                },


                // -----------------------------------------
                // CONTINUE
                // -----------------------------------------

                onContinue = {

                    navController.navigate(
                        KarmikaRoute.CustomerOnboarding.route
                    )
                }
            )
        }


        // =================================================
        // CUSTOMER ONBOARDING
        // =================================================
        //
        // This handles customer-specific setup.
        //
        // Example:
        // - Address
        // - Location
        // - Other customer information
        //
        // After completion, we DON'T navigate directly
        // to CustomerHome.
        //
        // We enter CustomerNavGraph.
        // =================================================

        composable(
            route = KarmikaRoute.CustomerOnboarding.route
        ) {

            val customerOnboardingViewModel:
                    CustomerOnboardingViewModel =
                viewModel()


            CustomerOnboardingScreen(

                viewModel = customerOnboardingViewModel,


                // -----------------------------------------
                // BACK
                // -----------------------------------------

                onBack = {

                    navController.popBackStack()
                },


                // -----------------------------------------
                // FINISH CUSTOMER SETUP
                // -----------------------------------------

                onContinue = {

                    navController.navigate(
                        KarmikaRoute.CustomerApp.route
                    ) {

                        popUpTo(
                            KarmikaRoute.CustomerOnboarding.route
                        ) {

                            inclusive = true
                        }
                    }
                }
            )
        }


        // =================================================
        // CUSTOMER APP
        // =================================================
        //
        // IMPORTANT:
        //
        // AppNavHost only ENTERS the customer application.
        //
        // CustomerNavGraph handles:
        //
        // - Home
        // - Search
        // - Bookings
        // - Notifications
        // - Profile
        // - Customer top bar
        // - Customer bottom bar
        //
        // =================================================

        composable(
            route = KarmikaRoute.CustomerApp.route
        ) {

            CustomerNavGraph(

                onLogout = {

                    // -------------------------------------
                    // CUSTOMER LOGOUT
                    // -------------------------------------

                    navController.navigate(
                        KarmikaRoute.Login.route
                    ) {

                        popUpTo(
                            KarmikaRoute.CustomerApp.route
                        ) {

                            inclusive = true
                        }
                    }
                }
            )
        }


        // =================================================
        // WORKER ONBOARDING
        // =================================================

        composable(
            route = KarmikaRoute.WorkerOnboarding.route
        ) {

            Text(
                text = "Worker Onboarding"
            )
        }


        // =================================================
        // WORKER APP
        // =================================================
        //
        // Later we will replace this with:
        //
        // WorkerNavGraph()
        //
        // =================================================

        composable(
            route = KarmikaRoute.WorkerHome.route
        ) {

            Text(
                text = "Worker Home"
            )
        }


        // =================================================
        // WORKER JOBS
        // =================================================

        composable(
            route = KarmikaRoute.WorkerJobs.route
        ) {

            Text(
                text = "Worker Jobs"
            )
        }


        // =================================================
        // WORKER EARNINGS
        // =================================================

        composable(
            route = KarmikaRoute.WorkerEarnings.route
        ) {

            Text(
                text = "Worker Earnings"
            )
        }


        // =================================================
        // WORKER PROFILE
        // =================================================

        composable(
            route = KarmikaRoute.WorkerProfile.route
        ) {

            Text(
                text = "Worker Profile"
            )
        }


        // =================================================
        // COMPANY ONBOARDING
        // =================================================

        composable(
            route = KarmikaRoute.CompanyOnboarding.route
        ) {

            Text(
                text = "Company Onboarding"
            )
        }


        // =================================================
        // COMPANY DASHBOARD
        // =================================================

        composable(
            route = KarmikaRoute.CompanyDashboard.route
        ) {

            Text(
                text = "Company Dashboard"
            )
        }


        // =================================================
        // COMPANY WORKERS
        // =================================================

        composable(
            route = KarmikaRoute.CompanyWorkers.route
        ) {

            Text(
                text = "Company Workers"
            )
        }


        // =================================================
        // COMPANY JOBS
        // =================================================

        composable(
            route = KarmikaRoute.CompanyJobs.route
        ) {

            Text(
                text = "Company Jobs"
            )
        }


        // =================================================
        // COMPANY PROFILE
        // =================================================

        composable(
            route = KarmikaRoute.CompanyProfile.route
        ) {

            Text(
                text = "Company Profile"
            )
        }


        // =================================================
        // OTP
        // =================================================

        composable(
            route = KarmikaRoute.Otp.route
        ) {

            Text(
                text = "OTP Screen"
            )
        }


        // =================================================
        // ADMIN DASHBOARD
        // =================================================

        composable(
            route = KarmikaRoute.AdminDashboard.route
        ) {

            Text(
                text = "Admin Dashboard"
            )
        }


        // =================================================
        // SUPER ADMIN DASHBOARD
        // =================================================

        composable(
            route = KarmikaRoute.SuperAdminDashboard.route
        ) {

            Text(
                text = "Super Admin Dashboard"
            )
        }
    }
}