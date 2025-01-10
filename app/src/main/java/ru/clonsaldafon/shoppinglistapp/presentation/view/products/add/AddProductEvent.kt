package ru.clonsaldafon.shoppinglistapp.presentation.view.products.add

import ru.clonsaldafon.shoppinglistapp.data.model.Category
import ru.clonsaldafon.shoppinglistapp.data.model.ProductByCategory

sealed class AddProductEvent {
    data class OnCategoryChanged(val value: Category) : AddProductEvent()
    data class OnProductChanged(val value: ProductByCategory) : AddProductEvent()
    data class OnQuantityChanged(val value: String) : AddProductEvent()
    data class OnSubmit(
        val productId: Int,
        val quantity: Int,
        val onComplete: (isSuccess: Boolean?,
                         quantityErrorMessage: String?) -> Unit
    ) : AddProductEvent()
}