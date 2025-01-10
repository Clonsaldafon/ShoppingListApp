package ru.clonsaldafon.shoppinglistapp.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_id")
    val productId: Int?,
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("quantity")
    val quantity: Int?,
    @SerializedName("added_by")
    val addedBy: String?,
    @SerializedName("bought_by")
    val boughtBy: String?,
    @SerializedName("created_at")
    val createdAt: String?
)
