package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import ru.clonsaldafon.shoppinglistapp.data.model.Product

data class ProductsUiState(
    val groupId: String = "",
    val groupName: String = "",
    val products: List<Product>? = null,
    val isLoading: Boolean = true
) {

    fun reset() = copy(
        groupId = "",
        groupName = "",
        products = null,
        isLoading = true
    )

}