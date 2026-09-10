package com.example.karmika.app.navigation

import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import com.example.karmika.core.common.model.UserRole
import com.example.karmika.data.local.LocalUserStorage

import com.example.karmika.feature.auth.login.LoginScreen
import com.example.karmika.feature.auth.login.LoginViewModel

import com.example.karmika.feature.auth.register.RegisterScreen
import com.example.karmika.feature.auth.register.RegisterViewModel

import com.example.karmika.feature.auth.onboarding.common.OnboardingRoleScreen


fun NavGraphBuilder.authNavGraph(
    localUserStorage: LocalUserStorage,

    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,

    onLoginSuccess: () -> Unit,
    onRegistrationSuccess: () -> Unit,

    onCustomerSelected: () -> Unit,
    onWorkerSelected: () -> Unit,
    onCompanySelected: () -> Unit
) {

    // =====================================================
    // SPLASH
    // =====================================================

    composable(
        route = KarmikaRoute.Splash.route
    ) {

        Text(
            text = "Splash Screen"
        )
    }


    // =====================================================
    // LOGIN
    // =====================================================

    composable(
        route = KarmikaRoute.Login.route
    ) {

        LoginScreen(

            viewModel = viewModel(
                factory = LoginViewModel.Factory(
                    localUserStorage
                )
            ),

            onRegisterClick = {
                onRegisterClick()
            },

            onForgotPasswordClick = {
                // TODO: Implement later
            },

            onLoginSuccess = {
                onLoginSuccess()
            }
        )
    }


    // =====================================================
    // REGISTER
    // =====================================================

    composable(
        route = KarmikaRoute.Register.route
    ) {

        RegisterScreen(

            viewModel = viewModel(
                factory = RegisterViewModel.Factory(
                    localUserStorage
                )
            ),

            onLoginClick = {
                onLoginClick()
            },

            onRegistrationSuccess = {
                onRegistrationSuccess()
            }
        )
    }


    // =====================================================
    // ONBOARDING ROLE SELECTION
    // =====================================================

    composable(
        route = KarmikaRoute.Onboarding.route
    ) {

        OnboardingRoleScreen(

            onContinue = { selectedRole ->

                when (selectedRole) {

                    UserRole.HOUSEHOLD -> {
                        onCustomerSelected()
                    }

                    UserRole.WORKER -> {
                        onWorkerSelected()
                    }

                    UserRole.COMPANY -> {
                        onCompanySelected()
                    }

                    UserRole.ORGANIZATION -> {
                        onCompanySelected()
                    }
                }
            }
        )
    }
}