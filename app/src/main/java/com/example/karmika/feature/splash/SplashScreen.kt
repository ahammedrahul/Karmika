package com.example.karmika.feature.splash

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.karmika.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    // Icons representing: Customer, Worker, Company, Services, Trust
    val loaderIcons = remember {
        listOf(
            Icons.Default.Person,       // Customer
            Icons.Default.Engineering,  // Worker
            Icons.Default.Business,     // Company
            Icons.Default.Build,        // Services
            Icons.Default.VerifiedUser  // Trust
        )
    }

    var currentIconIndex by remember { mutableIntStateOf(0) }

    // Rotate through icons continuously
    LaunchedEffect(Unit) {
        while (isActive) {
            delay(800) // Change icon every 800ms
            currentIconIndex = (currentIconIndex + 1) % loaderIcons.size
        }
    }

    // Navigation delay (adjust or replace with auth check logic)
    LaunchedEffect(Unit) {
        delay(4000) // Total splash duration
        onSplashFinished()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Karmika Logo",
            modifier = Modifier.size(130.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // App Name
        Text(
            text = "Karmika",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // Tagline
        Text(
            text = "Skilled People. Stronger Communities.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Dynamic Loader Container
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(72.dp)
        ) {
            // Circular Progress Indicator
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4.dp
            )

            // Animated Center Icon
            AnimatedContent(
                targetState = loaderIcons[currentIconIndex],
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "LoaderIconTransition"
            ) { icon ->
                Icon(
                    imageVector = icon,
                    contentDescription = "Loading indicator step",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}