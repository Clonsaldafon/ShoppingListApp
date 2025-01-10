package ru.clonsaldafon.shoppinglistapp.domain.group

import ru.clonsaldafon.shoppinglistapp.data.model.Product
import ru.clonsaldafon.shoppinglistapp.data.repository.GroupRepository
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
    private val repository: GroupRepository
) : GetProductsUseCase {

    override suspend fun invoke(groupId: Int): Result<List<Product>?> =
        repository.getProducts(groupId)

}