package com.aa1_nuncaesdemasiado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.aa1_nuncaesdemasiado.ui.theme.AA1_NuncaEsDemasiadoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AA1_NuncaEsDemasiadoTheme {
                var currentScreen by remember { mutableStateOf("login") }
                var selectedItem by remember { mutableStateOf<FeedItem?>(null) }

                when (currentScreen) {
                    "login" -> LoginScreen(
                        onContinue = { _ -> currentScreen = "feed" },
                        onNoAccount = { currentScreen = "register" }
                    )
                    "register" -> RegisterScreen(
                        onContinue = { _ -> currentScreen = "feed" },
                        onBack = { currentScreen = "login" }
                    )
                    "feed" -> FeedScreen(
                        onCategoryClick = { item ->
                            selectedItem = item
                            currentScreen = "detail"
                        },
                        onCardClick = { item ->
                            selectedItem = item
                            currentScreen = "detail"
                        }
                    )
                    "detail" -> {
                        selectedItem?.let { item ->
                            DetailScreen(item = item, onBack = { currentScreen = "feed" })
                        } ?: run {
                            currentScreen = "feed"
                        }
                    }
                }
            }
        }
    }
}
