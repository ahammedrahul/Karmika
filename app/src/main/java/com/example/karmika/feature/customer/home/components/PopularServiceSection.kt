package com.example.karmika.feature.customer.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class PopularServiceUiModel(
    val id: String,
    val name: String,
    val description: String,
    val rating: Double,
    val reviewCount: Int,
    val startingPrice: Int
)

@Composable
fun PopularServiceSection(
    services: List<PopularServiceUiModel>,
    onServiceClick: (PopularServiceUiModel) -> Unit,
    onSeeAllClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Popular Services",
                style = MaterialTheme.typography.titleLarge
            )

            TextButton(
                onClick = onSeeAllClick
            ) {
                Text(text = "See All")
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = services,
                key = { service -> service.id }
            ) { service ->

                PopularServiceCard(
                    serviceName = service.name,
                    rating = service.rating,
                    reviewCount = service.reviewCount,
                    startingPrice = service.startingPrice,
                    onClick = {
                        onServiceClick(service)
                    }
                )
            }
        }
    }
}