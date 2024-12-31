package ru.clonsaldafon.shoppinglistapp.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.clonsaldafon.shoppinglistapp.R
import ru.clonsaldafon.shoppinglistapp.presentation.groups.GroupsScreen
import ru.clonsaldafon.shoppinglistapp.presentation.login.LogInScreen
import ru.clonsaldafon.shoppinglistapp.presentation.signup.SignUpScreen

sealed class Routes(val route: String) {
    data object SignUp : Routes("signup")
    data object LogIn : Routes("login")
    data object Groups : Routes("groups")
    data object Profile : Routes("profile")
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
   NavHost(
       navController = navController,
       startDestination = Routes.Groups.route,
       enterTransition = {
           EnterTransition.None
       },
       exitTransition = {
           ExitTransition.None
       }
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

       composable(route = Routes.Groups.route) {
           GroupsScreen(
               modifier = modifier,
               navController = navController
           )
       }

       composable(route = Routes.Profile.route) {
           ProfileScreen(
               modifier = modifier,
               navController = navController
           )
       }
   }
}