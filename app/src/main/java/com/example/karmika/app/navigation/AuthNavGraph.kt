package com.example.karmika.app.navigation

import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import com.example.karmika.core.common.model.UserRole

import com.example.karmika.feature.auth.login.LoginScreen
import com.example.karmika.feature.auth.login.LoginViewModel

import com.example.karmika.feature.auth.register.RegisterScreen
import com.example.karmika.feature.auth.register.RegisterViewModel

import com.example.karmika.feature.auth.onboarding.common.OnboardingRoleScreen

fun NavGraphBuilder.authNavGraph(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,

    onLoginSuccess: () -> Unit,
    onRegistrationSuccess: () -> Unit,

    onRoleSelected: (UserRole) -> Unit
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

        val loginViewModel: LoginViewModel =
            hiltViewModel()

        LoginScreen(

            viewModel = loginViewModel,

            // ---------------------------------------------
            // REGISTER
            // ---------------------------------------------

            onRegisterClick = {
                onRegisterClick()
            },

            // ---------------------------------------------
            // FORGOT PASSWORD
            // ---------------------------------------------

            onForgotPasswordClick = {
                // TODO: Implement forgot password later.
            },

            // ---------------------------------------------
            // LOGIN SUCCESS
            // ---------------------------------------------

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

        val registerViewModel: RegisterViewModel =
            hiltViewModel()

        RegisterScreen(

            viewModel = registerViewModel,

            // ---------------------------------------------
            // GO TO LOGIN
            // ---------------------------------------------

            onLoginClick = {
                onLoginClick()
            },

            // ---------------------------------------------
            // REGISTRATION SUCCESS
            // ---------------------------------------------

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
                onRoleSelected(selectedRole)
            }
        )
    }
}