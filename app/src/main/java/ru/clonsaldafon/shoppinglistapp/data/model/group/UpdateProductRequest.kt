package ru.clonsaldafon.shoppinglistapp.data.model.group

import com.google.gson.annotations.SerializedName

data class UpdateProductRequest(
    @SerializedName("price")
    val price: Double?,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("status")
    val status: String
)
