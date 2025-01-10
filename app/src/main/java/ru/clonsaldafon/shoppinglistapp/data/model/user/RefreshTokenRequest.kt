package ru.clonsaldafon.shoppinglistapp.data.model.user

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(
    @SerializedName("refresh_token")
    val refreshToken: String
)
