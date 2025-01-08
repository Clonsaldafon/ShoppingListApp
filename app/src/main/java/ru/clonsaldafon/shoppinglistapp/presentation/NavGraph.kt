package ru.clonsaldafon.shoppinglistapp.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.clonsaldafon.shoppinglistapp.presentation.view.groups.GroupsScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.login.LogInScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.onboarding.OnboardingScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.products.GroupInfoScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.products.ProductsScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.profile.ProfileScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.signup.SignUpScreen

sealed class Routes(val route: String) {
    data object Onboarding : Routes("onboarding")
    data object SignUp : Routes("signup")
    data object LogIn : Routes("login")
    data object Groups : Routes("groups")
    data object Profile : Routes("profile")
    data object Products : Routes("products")
    data object GroupInfo : Routes("group_info")
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
       composable(route = Routes.Onboarding.route) {
           OnboardingScreen(
               navController = navController
           )
       }

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

       composable(route = Routes.Products.route) {
           ProductsScreen(
               modifier = modifier,
               navController = navController
           )
       }

       composable(route = Routes.GroupInfo.route) {
           GroupInfoScreen(
               modifier = modifier,
               navController = navController
           )
       }
   }
}