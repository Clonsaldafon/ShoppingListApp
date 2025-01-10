package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.UpdateProductRequest
import ru.clonsaldafon.shoppinglistapp.data.repository.GroupRepository
import javax.inject.Inject

class UpdateProductUseCaseImpl @Inject constructor(
    private val repository: GroupRepository
) : UpdateProductUseCase {

    override suspend fun invoke(
        groupId: String,
        productId: String,
        request: UpdateProductRequest
    ): Result<GroupResponse?> =
        repository.updateProduct(groupId, productId, request)

}