package ru.clonsaldafon.shoppinglistapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.clonsaldafon.shoppinglistapp.presentation.login.LogInScreen
import ru.clonsaldafon.shoppinglistapp.presentation.signup.SignUpScreen

sealed class Routes(val route: String) {
    data object SignUp : Routes("signup")
    data object LogIn : Routes("login")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
   NavHost(
       navController = navController,
       startDestination = Routes.SignUp.route
   ) {
       composable(route = Routes.SignUp.route) {
           SignUpScreen(
               modifier = modifier,
               navController = navController
           )
       }

       composable(route = Routes.LogIn.route) {
           LogInScreen(
               modifier = modifier,
               navController = navController
           )
       }
   }
}