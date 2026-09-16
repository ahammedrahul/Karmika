package com.example.karmika.feature.customer.service_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.karmika.domain.customer.model.Service
import com.example.karmika.feature.customer.service_details.components.ServiceDetailsActionButton
import com.example.karmika.feature.customer.service_details.components.ServiceDetailsHeader
import com.example.karmika.feature.customer.service_details.components.ServiceIncludedItems
import com.example.karmika.feature.customer.service_details.components.ServiceInfoSection
import com.example.karmika.feature.customer.service_details.components.ServiceRatingSection

@Composable
fun ServiceDetailsScreen(
    serviceId: String,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
    onBookNowClick: () -> Unit
) {

    val service = remember(serviceId) {
        staticServiceDetails[serviceId]
            ?: staticServiceDetails["service_1"]!!
    }

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 1.dp
            ) {
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                ) {

                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = onCloseClick
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Service Header
            ServiceDetailsHeader(
                serviceName = service.name,
                categoryName = service.categoryName,
                companyName = service.companyName
            )

            // Service Information
            ServiceInfoSection(
                description = service.description,
                price = service.price,
                duration = service.duration
            )

            // Rating and Reviews
            ServiceRatingSection(
                rating = service.rating,
                reviewCount = service.reviewCount
            )

            // Included Services
            ServiceIncludedItems(
                includedItems = service.includedItems
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // Request Booking
            ServiceDetailsActionButton(
                onBookNowClick = onBookNowClick
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}


/*
|--------------------------------------------------------------------------
| Static Service Data
|--------------------------------------------------------------------------
| Temporary data for Service Details.
| Later this can be replaced with API/Repository data.
|--------------------------------------------------------------------------
*/

private val staticServiceDetails = listOf(

    Service(
        id = "service_1",
        name = "Home Cleaning",
        description = "Complete home cleaning service for your house. Our trained professionals carefully clean your rooms, kitchen, bathrooms and other important areas.",
        categoryId = "cleaning",
        categoryName = "Cleaning",
        companyId = "company_1",
        companyName = "Karmika Home Services",
        price = 899,
        rating = 4.9,
        reviewCount = 150,
        duration = "2–3 hours",
        includedItems = listOf(
            "Dusting and cleaning of rooms",
            "Kitchen and bathroom cleaning",
            "Floor cleaning and sanitization",
            "Professional cleaning equipment"
        )
    ),

    Service(
        id = "service_2",
        name = "Plumbing Service",
        description = "Professional plumbing repair and maintenance for taps, pipes, leakage problems and other household plumbing needs.",
        categoryId = "plumbing",
        categoryName = "Plumbing",
        companyId = "company_2",
        companyName = "QuickFix Solutions",
        price = 399,
        rating = 4.6,
        reviewCount = 64,
        duration = "1–2 hours",
        includedItems = listOf(
            "Tap and pipe inspection",
            "Leakage detection",
            "Basic plumbing repair",
            "Professional tools and equipment"
        )
    ),

    Service(
        id = "service_3",
        name = "Electrical Repair",
        description = "Safe and reliable electrical repair services for common household electrical problems.",
        categoryId = "electrical",
        categoryName = "Electrical",
        companyId = "company_3",
        companyName = "Reliable Experts",
        price = 499,
        rating = 4.7,
        reviewCount = 98,
        duration = "1–2 hours",
        includedItems = listOf(
            "Electrical problem inspection",
            "Switch and socket repair",
            "Basic wiring inspection",
            "Safety-focused service"
        )
    ),

    Service(
        id = "service_4",
        name = "AC Repair",
        description = "Reliable air conditioner repair and maintenance to keep your home comfortable throughout the year.",
        categoryId = "ac",
        categoryName = "AC Repair",
        companyId = "company_4",
        companyName = "CoolCare Services",
        price = 499,
        rating = 4.7,
        reviewCount = 80,
        duration = "1–2 hours",
        includedItems = listOf(
            "AC performance inspection",
            "Filter cleaning",
            "Basic troubleshooting",
            "Cooling performance check"
        )
    ),

    Service(
        id = "service_5",
        name = "Painting Service",
        description = "Professional interior and exterior painting services for a fresh and beautiful home.",
        categoryId = "painting",
        categoryName = "Painting",
        companyId = "company_5",
        companyName = "ColorCraft Services",
        price = 999,
        rating = 4.8,
        reviewCount = 110,
        duration = "4–6 hours",
        includedItems = listOf(
            "Surface preparation",
            "Interior wall painting",
            "Basic finishing work",
            "Professional painting tools"
        )
    ),

    Service(
        id = "service_6",
        name = "Carpentry Service",
        description = "Furniture repair and professional carpentry work for your home.",
        categoryId = "carpentry",
        categoryName = "Carpentry",
        companyId = "company_6",
        companyName = "WoodWorks Experts",
        price = 599,
        rating = 4.7,
        reviewCount = 75,
        duration = "2–4 hours",
        includedItems = listOf(
            "Furniture inspection",
            "Basic furniture repair",
            "Door and cabinet adjustments",
            "Professional carpentry tools"
        )
    )

).associateBy { it.id }