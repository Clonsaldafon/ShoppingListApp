package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.group.AddProductRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.ProductResponse
import ru.clonsaldafon.shoppinglistapp.data.repository.GroupRepository
import javax.inject.Inject

class AddProductUseCaseImpl @Inject constructor(
    private val repository: GroupRepository
) : AddProductUseCase {

    override suspend fun invoke(
        groupId: String,
        request: AddProductRequest
    ): Result<ProductResponse?> =
        repository.addProduct(groupId, request)

}