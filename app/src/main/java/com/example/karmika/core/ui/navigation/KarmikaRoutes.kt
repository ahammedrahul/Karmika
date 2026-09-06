package com.example.karmika.core.ui.navigation

sealed class KarmikaRoute(
    val route: String
) {

    // Authentication
    data object Login : KarmikaRoute("login")

    data object Register : KarmikaRoute("register")

    data object Otp : KarmikaRoute("otp")

    data object Onboarding : KarmikaRoute("onboarding")


    // Customer
    data object CustomerHome : KarmikaRoute("customer/home")

    data object CustomerSearch : KarmikaRoute("customer/search")

    data object CustomerBooking : KarmikaRoute("customer/booking")

    data object CustomerProfile : KarmikaRoute("customer/profile")


    // Worker
    data object WorkerHome : KarmikaRoute("worker/home")

    data object WorkerJobs : KarmikaRoute("worker/jobs")

    data object WorkerEarnings : KarmikaRoute("worker/earnings")

    data object WorkerProfile : KarmikaRoute("worker/profile")


    // Company
    data object CompanyDashboard : KarmikaRoute("company/dashboard")

    data object CompanyWorkers : KarmikaRoute("company/workers")

    data object CompanyJobs : KarmikaRoute("company/jobs")

    data object CompanyProfile : KarmikaRoute("company/profile")


    // Future Admin
    data object AdminDashboard : KarmikaRoute("admin/dashboard")

    data object SuperAdminDashboard : KarmikaRoute("super-admin/dashboard")
}