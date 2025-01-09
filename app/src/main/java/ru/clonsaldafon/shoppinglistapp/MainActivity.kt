package ru.clonsaldafon.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.clonsaldafon.shoppinglistapp.presentation.navigation.NavGraph
import ru.clonsaldafon.shoppinglistapp.ui.theme.ShoppingListAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShoppingListAppTheme {
                val navController = rememberNavController()

                NavGraph(
                    navController = navController
                )
            }
        }
    }
}