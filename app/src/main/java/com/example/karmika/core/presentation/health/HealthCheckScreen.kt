package com.example.karmika.core.presentation.health

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HealthCheckScreen(
    viewModel: HealthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Karmika Backend Health Check",
            style = MaterialTheme.typography.headlineSmall
        )

        Button(
            onClick = { viewModel.checkBackendHealth() },
            modifier = Modifier.padding(top = 16.dp),
            enabled = !uiState.isLoading
        ) {
            Text("Test Backend Connection")
        }

        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        uiState.status?.let { status ->
            Text(
                text = "Backend status: $status",
                modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        uiState.error?.let { error ->
            Text(
                text = "Error: $error",
                modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}