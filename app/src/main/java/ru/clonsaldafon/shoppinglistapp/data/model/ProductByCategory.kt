package ru.clonsaldafon.shoppinglistapp.data.model

import com.google.gson.annotations.SerializedName

data class ProductByCategory(
    @SerializedName("category_id")
    val categoryId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("product_name_id")
    val productNameId: Int?
)
