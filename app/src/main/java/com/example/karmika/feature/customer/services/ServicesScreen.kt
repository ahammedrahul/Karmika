package com.example.karmika.feature.customer.services

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.domain.customer.model.Service

private val BackgroundColor = Color(0xFFF7FCF9)
private val CardColor = Color.White
private val SoftGreen = Color(0xFFE8F6EF)
private val BorderGreen = Color(0xFFD9EDE2)
private val PrimaryGreen = Color(0xFF078B5A)
private val DarkText = Color(0xFF10251D)
private val SecondaryText = Color(0xFF718078)
private val StarColor = Color(0xFFFFB000)

@Composable
fun ServicesScreen(
    categoryId: String,
    onServiceClick: (Service) -> Unit,
    onBackClick: () -> Unit
) {
    val services = remember(categoryId) {
        staticServices.filter { service ->
            categoryId == "all" || service.categoryId == categoryId
        }
    }

    val categoryTitle = remember(categoryId) {
        when (categoryId) {
            "cleaning" -> "Cleaning"
            "plumbing" -> "Plumbing"
            "electrician" -> "Electrician"
            "painting" -> "Painting"
            "carpentry" -> "Carpentry"
            "ac_repair" -> "AC Repair"
            else -> "All Services"
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ServicesHeader(
                title = categoryTitle,
                serviceCount = services.size,
                onBackClick = onBackClick
            )

            if (services.isEmpty()) {
                EmptyServicesView()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = 14.dp,
                        end = 14.dp,
                        top = 10.dp,
                        bottom = 20.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 2.dp,
                                    vertical = 2.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${services.size} Services",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = DarkText
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Text(
                                    text = "Sort",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = SecondaryText
                                )

                                Icon(
                                    imageVector = Icons.Rounded.FilterList,
                                    contentDescription = "Sort services",
                                    tint = PrimaryGreen,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    items(
                        items = services,
                        key = { service -> service.id }
                    ) { service ->
                        ServiceItemCard(
                            service = service,
                            onClick = {
                                onServiceClick(service)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ServicesHeader(
    title: String,
    serviceCount: Int,
    onBackClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = CardColor,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 14.dp,
                    vertical = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CardColor)
                    .border(
                        width = 1.dp,
                        color = BorderGreen,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = DarkText,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(SoftGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Build,
                    contentDescription = "Service category",
                    tint = PrimaryGreen,
                    modifier = Modifier.size(25.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = DarkText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "$serviceCount services available",
                    style = MaterialTheme.typography.bodySmall,
                    color = SecondaryText
                )
            }
        }
    }
}

@Composable
private fun ServiceItemCard(
    service: Service,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = BorderGreen,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(78.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(SoftGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Build,
                    contentDescription = service.name,
                    tint = PrimaryGreen,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.width(11.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = DarkText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = service.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        lineHeight = 15.sp
                    ),
                    color = SecondaryText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = service.companyName,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = PrimaryGreen,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Rating",
                        tint = StarColor,
                        modifier = Modifier.size(15.dp)
                    )

                    Spacer(modifier = Modifier.width(3.dp))

                    Text(
                        text = "${service.rating}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = DarkText
                    )

                    Text(
                        text = " (${service.reviewCount})",
                        style = MaterialTheme.typography.labelSmall,
                        color = SecondaryText
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "From ",
                        style = MaterialTheme.typography.labelSmall,
                        color = SecondaryText
                    )

                    Text(
                        text = "₹${service.price}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = PrimaryGreen
                    )
                }
            }

            Spacer(modifier = Modifier.width(5.dp))

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                contentDescription = "View service details",
                tint = SecondaryText,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun EmptyServicesView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(SoftGreen),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Build,
                    contentDescription = "No services",
                    tint = PrimaryGreen,
                    modifier = Modifier.size(38.dp)
                )
            }

            Text(
                text = "No services available",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = DarkText
            )

            Text(
                text = "Try selecting another service category.",
                style = MaterialTheme.typography.bodyMedium,
                color = SecondaryText
            )
        }
    }
}

private val staticServices = listOf(
    Service(
        id = "service_1",
        name = "General Plumbing Service",
        description = "Tap repair, pipe fixing, leakage solutions and more.",
        categoryId = "plumbing",
        categoryName = "Plumbing",
        companyId = "company_1",
        companyName = "Karmika Home Services",
        price = 299,
        rating = 4.8,
        reviewCount = 120,
        duration = "1–2 hours"
    ),
    Service(
        id = "service_2",
        name = "Electrical Repair",
        description = "Reliable electrical installation and repair services.",
        categoryId = "electrician",
        categoryName = "Electrician",
        companyId = "company_2",
        companyName = "Reliable Service Solutions",
        price = 399,
        rating = 4.7,
        reviewCount = 95,
        duration = "1–2 hours"
    ),
    Service(
        id = "service_3",
        name = "Home Cleaning",
        description = "Complete home cleaning service for your house.",
        categoryId = "cleaning",
        categoryName = "Cleaning",
        companyId = "company_1",
        companyName = "Karmika Home Services",
        price = 899,
        rating = 4.9,
        reviewCount = 150,
        duration = "2–3 hours"
    ),
    Service(
        id = "service_4",
        name = "AC Repair",
        description = "Fast and reliable air conditioner repair service.",
        categoryId = "ac_repair",
        categoryName = "AC Repair",
        companyId = "company_3",
        companyName = "QuickFix Professionals",
        price = 499,
        rating = 4.6,
        reviewCount = 80,
        duration = "1–2 hours"
    ),
    Service(
        id = "service_5",
        name = "Painting Service",
        description = "Professional interior and exterior home painting.",
        categoryId = "painting",
        categoryName = "Painting",
        companyId = "company_2",
        companyName = "Reliable Service Solutions",
        price = 999,
        rating = 4.8,
        reviewCount = 110,
        duration = "4–6 hours"
    ),
    Service(
        id = "service_6",
        name = "Carpentry Service",
        description = "Furniture repair and professional carpentry work.",
        categoryId = "carpentry",
        categoryName = "Carpentry",
        companyId = "company_3",
        companyName = "QuickFix Professionals",
        price = 599,
        rating = 4.7,
        reviewCount = 75,
        duration = "2–4 hours"
    )
)