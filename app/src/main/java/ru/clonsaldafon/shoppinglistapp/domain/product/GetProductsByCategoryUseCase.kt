package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.ProductByCategory

interface GetProductsByCategoryUseCase {

    suspend operator fun invoke(categoryId: Int): Result<List<ProductByCategory>?>

}