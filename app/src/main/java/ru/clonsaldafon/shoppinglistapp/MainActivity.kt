package ru.clonsaldafon.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ru.clonsaldafon.shoppinglistapp.presentation.signup.SignUpScreen
import ru.clonsaldafon.shoppinglistapp.ui.theme.ShoppingListAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListAppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}