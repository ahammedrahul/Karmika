package com.example.karmika.feature.auth.onboarding.common

sealed class OnboardingRoute(
    val route: String
) {

    data object SelectRole : OnboardingRoute(
        "onboarding/select-role"
    )

    data object CustomerProfile : OnboardingRoute(
        "onboarding/customer/profile"
    )

    data object CustomerAddress : OnboardingRoute(
        "onboarding/customer/address"
    )

    data object WorkerProfile : OnboardingRoute(
        "onboarding/worker/profile"
    )

    data object WorkerAddress : OnboardingRoute(
        "onboarding/worker/address"
    )

    data object CompanyProfile : OnboardingRoute(
        "onboarding/company/profile"
    )

    data object CompanyAddress : OnboardingRoute(
        "onboarding/company/address"
    )
}