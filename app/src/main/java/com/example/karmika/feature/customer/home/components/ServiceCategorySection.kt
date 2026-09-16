package com.example.karmika.feature.customer.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Carpenter
import androidx.compose.material.icons.outlined.CleaningServices
import androidx.compose.material.icons.outlined.ElectricalServices
import androidx.compose.material.icons.outlined.FormatPaint
import androidx.compose.material.icons.outlined.Plumbing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class ServiceCategoryUiModel(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val iconColor: Color,
    val iconBackgroundColor: Color
)

@Composable
fun ServiceCategorySection(
    categories: List<ServiceCategoryUiModel>,
    onCategoryClick: (ServiceCategoryUiModel) -> Unit,
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
                text = "Service Categories",
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
                items = categories,
                key = { category -> category.id }
            ) { category ->

                ServiceCategoryCard(
                    categoryName = category.name,
                    categoryIcon = category.icon,
                    iconColor = category.iconColor,
                    iconBackgroundColor = category.iconBackgroundColor,
                    onClick = {
                        onCategoryClick(category)
                    }
                )
            }
        }
    }
}