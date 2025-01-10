package ru.clonsaldafon.shoppinglistapp.presentation.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.clonsaldafon.shoppinglistapp.presentation.view.groups.create.CreateGroupScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.groups.GroupsScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.groups.join.JoinToGroupScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.login.LogInScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.onboarding.OnboardingScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.products.add.AddProductScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.products.group.GroupInfoScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.products.ProductsScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.profile.ProfileScreen
import ru.clonsaldafon.shoppinglistapp.presentation.view.signup.SignUpScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: NavGraphViewModel = hiltViewModel()
) {
    val token by viewModel.userEntity.observeAsState()

    val startDestination = Routes.LogIn.route
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
            val groupId = it.arguments?.getString("group_id")
            val groupName = it.arguments?.getString("group_name")
            val groupDescription = it.arguments?.getString("group_description")
            val code = it.arguments?.getString("code")

            ProductsScreen(
                modifier = modifier,
                navController = navController,
                groupId = groupId,
                groupName = groupName,
                groupDescription = groupDescription,
                code = code
            )
        }

        composable(route = Routes.AddProduct.route) {
            val groupId = it.arguments?.getString("group_id")
            val groupName = it.arguments?.getString("group_name")
            val groupDescription = it.arguments?.getString("group_description")
            val code = it.arguments?.getString("code")

            AddProductScreen(
                navController = navController,
                groupId = groupId,
                groupName = groupName,
                groupDescription = groupDescription,
                code = code
            )
        }

        composable(route = Routes.GroupInfo.route) {
            val groupId = it.arguments?.getString("group_id")
            val groupName = it.arguments?.getString("group_name")
            val groupDescription = it.arguments?.getString("group_description")
            val code = it.arguments?.getString("code")

            GroupInfoScreen(
                modifier = modifier,
                navController = navController,
                groupId = groupId,
                groupName = groupName,
                groupDescription = groupDescription,
                code = code
            )
        }
    }
}