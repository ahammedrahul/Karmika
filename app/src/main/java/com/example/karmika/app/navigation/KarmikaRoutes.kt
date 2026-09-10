package com.example.karmika.app.navigation

sealed class KarmikaRoute(
    val route: String
) {

    // =====================================================
    // AUTHENTICATION
    // =====================================================

    data object Splash : KarmikaRoute("splash")

    data object Login : KarmikaRoute("login")

    data object Register : KarmikaRoute("register")

    data object Otp : KarmikaRoute("otp")

    data object Onboarding : KarmikaRoute("onboarding")


    // =====================================================
    // COMMON ONBOARDING
    // =====================================================

    data object ProfileStep :
        KarmikaRoute("onboarding/profile")


    // =====================================================
    // CUSTOMER
    // =====================================================

    data object CustomerOnboarding :
        KarmikaRoute("customer/onboarding")

    data object CustomerHome :
        KarmikaRoute("customer/home")

    data object CustomerSearch :
        KarmikaRoute("customer/search")

    data object CustomerBooking :
        KarmikaRoute("customer/booking")

    data object CustomerProfile :
        KarmikaRoute("customer/profile")
    data object CustomerNotifications :
        KarmikaRoute("customer/notifications")
    data object CustomerApp :
        KarmikaRoute("customer")


    // =====================================================
    // WORKER
    // =====================================================

    data object WorkerOnboarding :
        KarmikaRoute("worker/onboarding")

    data object WorkerHome :
        KarmikaRoute("worker/home")

    data object WorkerJobs :
        KarmikaRoute("worker/jobs")

    data object WorkerEarnings :
        KarmikaRoute("worker/earnings")

    data object WorkerProfile :
        KarmikaRoute("worker/profile")


    // =====================================================
    // COMPANY
    // =====================================================

    data object CompanyOnboarding :
        KarmikaRoute("company/onboarding")

    data object CompanyDashboard :
        KarmikaRoute("company/dashboard")

    data object CompanyWorkers :
        KarmikaRoute("company/workers")

    data object CompanyJobs :
        KarmikaRoute("company/jobs")

    data object CompanyProfile :
        KarmikaRoute("company/profile")


    // =====================================================
    // FUTURE ADMIN
    // =====================================================

    data object AdminDashboard :
        KarmikaRoute("admin/dashboard")

    data object SuperAdminDashboard :
        KarmikaRoute("super-admin/dashboard")
}