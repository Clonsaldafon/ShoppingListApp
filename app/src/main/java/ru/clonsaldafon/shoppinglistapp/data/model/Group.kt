package ru.clonsaldafon.shoppinglistapp.data.model

import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("groupID")
    val id: Int,
    val name: String,
    val description: String,
    val code: String
)
