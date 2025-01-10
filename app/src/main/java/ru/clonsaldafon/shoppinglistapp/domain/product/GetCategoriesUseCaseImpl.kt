package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.Category
import ru.clonsaldafon.shoppinglistapp.data.repository.ProductRepository
import javax.inject.Inject

class GetCategoriesUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) : GetCategoriesUseCase {

    override suspend fun invoke(): Result<List<Category>?> =
        repository.getCategories()

}