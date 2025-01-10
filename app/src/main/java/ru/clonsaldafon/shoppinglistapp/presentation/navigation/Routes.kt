package ru.clonsaldafon.shoppinglistapp.presentation.navigation

sealed class Routes(val route: String) {
    data object Onboarding : Routes("onboarding")
    data object SignUp : Routes("signup")
    data object LogIn : Routes("login")
    data object Groups : Routes("groups")
    data object CreateGroup : Routes("create_group")
    data object JoinToGroup : Routes("join_to_group")
    data object Profile : Routes("profile")
    data object Products : Routes(
        "products/{group_id}/{group_name}/{group_description}/{code}"
    ) {
        fun createRoute(
            groupId: String?,
            groupName: String?,
            groupDescription: String?,
            code: String?
        ) = "products/$groupId/$groupName/$groupDescription/$code"
    }
    data object AddProduct : Routes(
        "add_product/{group_id}/{group_name}/{group_description}/{code}"
    ) {
        fun createRoute(
            groupId: String?,
            groupName: String?,
            groupDescription: String?,
            code: String?
        ) = "add_product/$groupId/$groupName/$groupDescription/$code"
    }
    data object GroupInfo : Routes(
        "group_info/{group_id}/{group_name}/{group_description}/{code}"
    ) {
        fun createRoute(
            groupId: String?,
            groupName: String?,
            groupDescription: String?,
            code: String?
        ) = "group_info/$groupId/$groupName/$groupDescription/$code"
    }
}