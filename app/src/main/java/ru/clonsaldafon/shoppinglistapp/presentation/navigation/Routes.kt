package ru.clonsaldafon.shoppinglistapp.presentation.navigation

sealed class Routes(val route: String) {
    data object Onboarding : Routes("onboarding")
    data object SignUp : Routes("signup")
    data object LogIn : Routes("login")
    data object Groups : Routes("groups")
    data object CreateGroup : Routes("create_group")
    data object JoinToGroup : Routes("join_to_group")
    data object Profile : Routes("profile")
    data object Products : Routes("products")
    data object AddProduct : Routes("add_product")
    data object GroupInfo : Routes("group_info")
}