package com.example.karmika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.karmika.app.navigation.AppNavHost
import com.example.karmika.core.ui.theme.KarmikaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            KarmikaTheme {

                AppNavHost()

            }
        }
    }
}