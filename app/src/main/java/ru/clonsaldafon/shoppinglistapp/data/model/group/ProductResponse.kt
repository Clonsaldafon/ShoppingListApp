package ru.clonsaldafon.shoppinglistapp.data.model.group

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("product_id")
    val productId: Int?
)
