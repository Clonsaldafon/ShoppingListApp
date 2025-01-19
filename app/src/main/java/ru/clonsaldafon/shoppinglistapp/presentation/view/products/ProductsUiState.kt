package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import ru.clonsaldafon.shoppinglistapp.data.model.Product

data class ProductsUiState(
    val groupId: String = "",
    val groupName: String = "",
    val products: List<Product>? = null,
    val isBuyWindowHidden: Boolean = true,
    val currentPrice: String? = null,
    val currentProductId: String = "",
    val currentQuantity: Int = 0,
    val isCurrentBought: Boolean = false,
    val isCurrentPriceInvalid: Boolean = false,
    val currentPriceErrorMessage: String = "",
    val isLoading: Boolean = true,
    val error: String = ""
) {

    fun reset() = copy(
        groupId = "",
        groupName = "",
        products = null,
        isBuyWindowHidden = true,
        currentPrice = null,
        currentProductId = "",
        isCurrentBought = false,
        isCurrentPriceInvalid = false,
        currentPriceErrorMessage = "",
        isLoading = true,
        error = ""
    )

    fun resetCurrentValues() = copy(
        currentPrice = null,
        currentProductId = "",
        currentQuantity = 0,
        isCurrentBought = false,
        isCurrentPriceInvalid = false,
        currentPriceErrorMessage = "",
        isBuyWindowHidden = true,
        isLoading = false,
        error = ""
    )

    val isValid: Boolean
        get() = !isCurrentPriceInvalid &&
                currentPrice?.isNotEmpty() == true

}