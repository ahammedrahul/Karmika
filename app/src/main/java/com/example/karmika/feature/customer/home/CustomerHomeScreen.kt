package com.example.karmika.feature.customer.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Carpenter
import androidx.compose.material.icons.outlined.CleaningServices
import androidx.compose.material.icons.outlined.ElectricalServices
import androidx.compose.material.icons.outlined.FormatPaint
import androidx.compose.material.icons.outlined.Plumbing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.karmika.feature.customer.home.components.HomeBanner
import com.example.karmika.feature.customer.home.components.HomeHeader
import com.example.karmika.feature.customer.home.components.HomeSearchBar
import com.example.karmika.feature.customer.home.components.PopularServiceSection
import com.example.karmika.feature.customer.home.components.PopularServiceUiModel
import com.example.karmika.feature.customer.home.components.RecommendedCompanySection
import com.example.karmika.feature.customer.home.components.RecommendedCompanyUiModel
import com.example.karmika.feature.customer.home.components.ServiceCategorySection
import com.example.karmika.feature.customer.home.components.ServiceCategoryUiModel

// ==========================================================
// DATA MODELS
// ==========================================================

data class RecentBookingUiModel(
    val id: String,
    val serviceName: String,
    val status: String,
    val timeLabel: String
)

// ==========================================================
// MAIN SCREEN
// ==========================================================

@Composable
fun CustomerHomeScreen(
    onSearchClick: () -> Unit,

    // Category click
    onServiceClick: (String) -> Unit,

    // Popular service click
    onPopularServiceClick: (String) -> Unit,

    customerName: String = "Customer",

    categories: List<ServiceCategoryUiModel> =
        SampleHomeData.serviceCategories,

    popularServices: List<PopularServiceUiModel> =
        SampleHomeData.popularServices,

    recommendedCompanies: List<RecommendedCompanyUiModel> =
        SampleHomeData.recommendedCompanies,

    recentBooking: RecentBookingUiModel? =
        SampleHomeData.recentBooking
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        HomeContent(
            customerName = customerName,
            categories = categories,
            popularServices = popularServices,
            recommendedCompanies = recommendedCompanies,
            recentBooking = recentBooking,
            onSearchClick = onSearchClick,
            onServiceClick = onServiceClick,
            onPopularServiceClick = onPopularServiceClick
        )
    }
}

// ==========================================================
// HOME CONTENT
// ==========================================================

@Composable
private fun HomeContent(
    customerName: String,
    categories: List<ServiceCategoryUiModel>,
    popularServices: List<PopularServiceUiModel>,
    recommendedCompanies: List<RecommendedCompanyUiModel>,
    recentBooking: RecentBookingUiModel?,
    onSearchClick: () -> Unit,
    onServiceClick: (String) -> Unit,
    onPopularServiceClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {

        // Greeting Header
        item {
            HomeHeader(
                customerName = customerName
            )
        }

        // Search Bar
        item {
            HomeSearchBar(
                onSearchClick = onSearchClick,
                onFilterClick = {
                    // TODO: Open filter modal
                }
            )
        }

        // Promotional Banner
        item {
            HomeBanner(
                onExploreClick = {
                    onServiceClick("all")
                }
            )
        }

        // ==================================================
        // SERVICE CATEGORIES
        // ==================================================

        if (categories.isNotEmpty()) {
            item {
                ServiceCategorySection(
                    categories = categories,

                    onCategoryClick = { category ->
                        onServiceClick(category.id)
                    },

                    onSeeAllClick = {
                        onServiceClick("all")
                    }
                )
            }
        }

        // ==================================================
        // POPULAR SERVICES
        // ==================================================

        if (popularServices.isNotEmpty()) {
            item {
                PopularServiceSection(
                    services = popularServices,

                    // Individual popular service
                    // → Directly open Service Details
                    onServiceClick = { service ->
                        onPopularServiceClick(service.id)
                    },

                    // See All
                    // → Open Services List
                    onSeeAllClick = {
                        onServiceClick("all")
                    }
                )
            }
        }

        // ==================================================
        // RECOMMENDED COMPANIES
        // ==================================================

        if (recommendedCompanies.isNotEmpty()) {
            item {
                RecommendedCompanySection(
                    companies = recommendedCompanies,

                    onCompanyClick = { company ->
                        // TODO: Navigate to company details
                    },

                    onFavoriteClick = { company ->
                        // TODO: Toggle company favorite
                    },

                    onSeeAllClick = {
                        // TODO: Navigate to all recommended companies
                    }
                )
            }
        }
    }
}

// ==========================================================
// SAMPLE / DEFAULT DATA
// ==========================================================

object SampleHomeData {

    val serviceCategories = listOf(

        ServiceCategoryUiModel(
            id = "cleaning",
            name = "Cleaning",
            icon = Icons.Outlined.CleaningServices,
            iconColor = Color(0xFF16A34A),
            iconBackgroundColor = Color(0xFFE8F7EE)
        ),

        ServiceCategoryUiModel(
            id = "plumbing",
            name = "Plumbing",
            icon = Icons.Outlined.Plumbing,
            iconColor = Color(0xFF2563EB),
            iconBackgroundColor = Color(0xFFE8F0FF)
        ),

        ServiceCategoryUiModel(
            id = "electrician",
            name = "Electrician",
            icon = Icons.Outlined.ElectricalServices,
            iconColor = Color(0xFFF59E0B),
            iconBackgroundColor = Color(0xFFFFF1DF)
        ),

        ServiceCategoryUiModel(
            id = "painting",
            name = "Painting",
            icon = Icons.Outlined.FormatPaint,
            iconColor = Color(0xFF9333EA),
            iconBackgroundColor = Color(0xFFF3E8FF)
        ),

        ServiceCategoryUiModel(
            id = "carpentry",
            name = "Carpentry",
            icon = Icons.Outlined.Carpenter,
            iconColor = Color(0xFF92400E),
            iconBackgroundColor = Color(0xFFF5EBDD)
        ),

        ServiceCategoryUiModel(
            id = "ac_repair",
            name = "AC Repair",
            icon = Icons.Outlined.AcUnit,
            iconColor = Color(0xFF0284C7),
            iconBackgroundColor = Color(0xFFE0F2FE)
        )
    )

    // ======================================================
    // IMPORTANT:
    // IDs here must match StaticServiceDetails.kt
    // ======================================================

    val popularServices = listOf(

        PopularServiceUiModel(
            id = "service_2",
            name = "Plumbing Service",
            description = "Professional plumbing services",
            rating = 4.8,
            reviewCount = 120,
            startingPrice = 299
        ),

        PopularServiceUiModel(
            id = "service_3",
            name = "Electrical Repair",
            description = "Reliable electrical repair service",
            rating = 4.7,
            reviewCount = 95,
            startingPrice = 399
        ),

        PopularServiceUiModel(
            id = "service_1",
            name = "Home Cleaning",
            description = "Complete home cleaning service",
            rating = 4.9,
            reviewCount = 150,
            startingPrice = 899
        ),

        PopularServiceUiModel(
            id = "service_4",
            name = "AC Repair",
            description = "Fast and reliable AC repair",
            rating = 4.6,
            reviewCount = 80,
            startingPrice = 499
        ),

        PopularServiceUiModel(
            id = "service_5",
            name = "Painting Service",
            description = "Professional home painting",
            rating = 4.8,
            reviewCount = 110,
            startingPrice = 999
        )
    )

    val recommendedCompanies = listOf(

        RecommendedCompanyUiModel(
            id = "company_1",
            name = "Karmika Home Experts",
            description = "Professional home repair and maintenance services",
            location = "Bengaluru",
            rating = 4.8,
            reviewCount = 124
        ),

        RecommendedCompanyUiModel(
            id = "company_2",
            name = "Reliable Service Solutions",
            description = "Trusted electricians, plumbers, and cleaning experts",
            location = "Bengaluru",
            rating = 4.6,
            reviewCount = 98
        ),

        RecommendedCompanyUiModel(
            id = "company_3",
            name = "QuickFix Professionals",
            description = "Fast and reliable services for your home",
            location = "Bengaluru",
            rating = 4.7,
            reviewCount = 87
        )
    )

    val recentBooking = RecentBookingUiModel(
        id = "booking_1",
        serviceName = "Plumbing Service",
        status = "Confirmed",
        timeLabel = "Tomorrow • 10:00 AM"
    )
}