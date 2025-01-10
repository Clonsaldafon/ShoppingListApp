package ru.clonsaldafon.shoppinglistapp.data.model

import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("member_id")
    val memberId: Int?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("role")
    val role: String?
)
