package ru.clonsaldafon.shoppinglistapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.presentation.view.groups.CreateGroupScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.groups.GroupsScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.groups.JoinToGroupScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.login.LogInScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.onboarding.OnboardingScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.products.AddProductScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.products.GroupInfoScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.products.ProductsScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.profile.ProfileScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.signup.SignUpScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: NavGraphViewModel = hiltViewModel()
) {
    val token by viewModel.token.observeAsState()

    val startDestination = Routes.Groups.route
//    val startDestination =
//        if (token == null) Routes.Onboarding.route
//        else Routes.Groups.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(tween(400)) },
        exitTransition = { ExitTransition.KeepUntilTransitionsFinished }
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

        composable(route = Routes.CreateGroup.route) {
            CreateGroupScreen(
                navController = navController
            )
        }

        composable(route = Routes.JoinToGroup.route) {
            JoinToGroupScreen(
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

        composable(route = Routes.AddProduct.route) {
            AddProductScreen(
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