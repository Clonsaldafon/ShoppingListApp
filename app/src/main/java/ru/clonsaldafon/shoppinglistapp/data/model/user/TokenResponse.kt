package ru.clonsaldafon.shoppinglistapp.data.model.user

data class TokenResponse(
    val accessToken: String?,
    val refreshToken: String?
)
