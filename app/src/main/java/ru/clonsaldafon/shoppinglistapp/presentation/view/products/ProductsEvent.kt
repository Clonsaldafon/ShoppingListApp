package ru.clonsaldafon.shoppinglistapp.presentation.view.products

import ru.clonsaldafon.shoppinglistapp.data.model.Product

sealed class ProductsEvent {
    data class OnProductDeleted(val groupId: String, val productId: String) : ProductsEvent()
    data class OnProductUpdated(val groupId: String, val productId: String) : ProductsEvent()
    data class OnBuyWindowVisibilityChanged(val value: Boolean) : ProductsEvent()
    data class OnProductsLoaded(val value: List<Product>) : ProductsEvent()
    data class OnCurrentPriceUpdated(val value: String) : ProductsEvent()
    data class OnCurrentProductIdUpdated(val value: String) : ProductsEvent()
    data class OnCurrentProductBought(val value: Boolean) : ProductsEvent()
    data class OnCurrentQuantityUpdated(val value: Int) : ProductsEvent()
}