package ru.clonsaldafon.shoppinglistapp.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category-id")
    val categoryId: Int?,
    @SerializedName("name")
    val name: String?
)
