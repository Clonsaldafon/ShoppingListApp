package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.ProductByCategory
import ru.clonsaldafon.shoppinglistapp.data.repository.ProductRepository
import javax.inject.Inject

class GetProductsByCategoryUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) : GetProductsByCategoryUseCase {

    override suspend fun invoke(categoryId: Int): Result<List<ProductByCategory>?> =
        repository.getProducts(categoryId)

}