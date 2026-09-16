package com.example.karmika.feature.customer.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class RecommendedCompanyUiModel(
    val id: String,
    val name: String,
    val description: String,
    val location: String,
    val rating: Double,
    val reviewCount: Int,
    val isFavorite: Boolean = false
)

@Composable
fun RecommendedCompanySection(
    companies: List<RecommendedCompanyUiModel>,
    onCompanyClick: (RecommendedCompanyUiModel) -> Unit,
    onFavoriteClick: (RecommendedCompanyUiModel) -> Unit,
    onSeeAllClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        // Section Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Recommended",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF0F172A)
                    )
                )
                Text(
                    text = "Top rated service providers near you",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )
                )
            }

            TextButton(onClick = onSeeAllClick) {
                Text(
                    text = "See All",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color(0xFF0F5A47),
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Cards List
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            companies.forEach { company ->
                RecommendedCompanyCard(
                    companyName = company.name,
                    companyDescription = company.description,
                    location = company.location,
                    rating = company.rating,
                    reviewCount = company.reviewCount,
                    onClick = { onCompanyClick(company) },
                    onFavoriteClick = { onFavoriteClick(company) }
                )
            }
        }
    }
}