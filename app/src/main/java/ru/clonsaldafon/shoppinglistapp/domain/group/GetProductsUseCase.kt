package ru.clonsaldafon.shoppinglistapp.domain.group

import ru.clonsaldafon.shoppinglistapp.data.model.Product

interface GetProductsUseCase {

    suspend operator fun invoke(groupId: Int): Result<List<Product>?>

}