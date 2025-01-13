package ru.clonsaldafon.shoppinglistapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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

                Scaffold { innerPadding ->
                    NavGraph(
                        modifier = Modifier
                            .padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}