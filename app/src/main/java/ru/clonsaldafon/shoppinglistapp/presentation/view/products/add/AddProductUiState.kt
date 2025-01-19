package ru.clonsaldafon.shoppinglistapp.presentation.view.products.add

import ru.clonsaldafon.shoppinglistapp.data.model.Category
import ru.clonsaldafon.shoppinglistapp.data.model.ProductByCategory

data class AddProductUiState(
    val groupId: String = "",
    val categories: List<Category>? = null,
    val category: String = "",
    val categoryId: Int = 0,
    val products: List<ProductByCategory>? = null,
    val product: String = "",
    val productId: Int = 0,
    val quantity: String = "",
    val isQuantityInvalid: Boolean = false,
    val quantityErrorMessage: String = "",
    val isLoading: Boolean = true,
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String = ""
) {

    fun reset() = copy(
        groupId = "",
        categories = null,
        category = "",
        categoryId = 0,
        products = null,
        product = "",
        productId = 0,
        quantity = "",
        isQuantityInvalid = false,
        quantityErrorMessage = "",
        isLoading = true,
        isSubmitting = false,
        isSuccess = false,
        error = ""
    )

    fun resetProduct() = copy(
        product = "",
        productId = 0
    )

    val isValid: Boolean
        get() = !isQuantityInvalid &&
                category.isNotEmpty() &&
                product.isNotEmpty() &&
                quantity.isNotEmpty()

}
