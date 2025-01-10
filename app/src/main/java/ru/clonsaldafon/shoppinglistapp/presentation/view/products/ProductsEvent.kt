package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import ru.clonsaldafon.shoppinglistapp.data.model.Product

sealed class ProductsEvent {
    data class OnProductDeleted(val groupId: String, val productId: String) : ProductsEvent()
    data class OnProductsLoaded(val value: List<Product>) : ProductsEvent()
}