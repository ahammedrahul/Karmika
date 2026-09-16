package com.example.karmika.feature.customer.services.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.karmika.feature.customer.services.ServiceSortOption

@Composable
fun ServiceSortMenu(
    selectedOption: ServiceSortOption,
    onOptionSelected: (ServiceSortOption) -> Unit
) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }

    Box {
        IconButton(
            onClick = {
                menuExpanded = true
            }
        ) {
            Icon(
                imageVector = Icons.Default.Sort,
                contentDescription = "Sort services"
            )
        }

        DropdownMenu(
            expanded = menuExpanded,
            onDismissRequest = {
                menuExpanded = false
            }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = "Relevance")
                },
                onClick = {
                    onOptionSelected(ServiceSortOption.RELEVANCE)
                    menuExpanded = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text(text = "Price: Low to High")
                },
                onClick = {
                    onOptionSelected(
                        ServiceSortOption.PRICE_LOW_TO_HIGH
                    )
                    menuExpanded = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text(text = "Price: High to Low")
                },
                onClick = {
                    onOptionSelected(
                        ServiceSortOption.PRICE_HIGH_TO_LOW
                    )
                    menuExpanded = false
                }
            )

            DropdownMenuItem(
                text = {
                    Text(text = "Highest Rated")
                },
                onClick = {
                    onOptionSelected(
                        ServiceSortOption.RATING_HIGH_TO_LOW
                    )
                    menuExpanded = false
                }
            )
        }
    }
}