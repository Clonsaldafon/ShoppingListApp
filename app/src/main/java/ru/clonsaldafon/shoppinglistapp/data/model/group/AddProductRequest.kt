package ru.clonsaldafon.shoppinglistapp.data.model.group

import com.google.gson.annotations.SerializedName

data class AddProductRequest(
    @SerializedName("product_name_id")
    val productNameId: Int,
    @SerializedName("quantity")
    val quantity: Int
)
